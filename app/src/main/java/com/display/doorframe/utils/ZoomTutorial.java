package com.display.doorframe.utils;


import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;

import com.display.doorframe.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class ZoomTutorial {
	
	final private int mAnimationDuration = 300;// 动画持续的时间，300比较合适
	private Animator mCurrentAnimator;//当前的动画对象
	
	private View mContainView;//当前屏幕中视图最外层的容器
	private ViewGroup mThumbViewParent;//小图片的视图
	private View mExpandedView;//大图片所在的视图
	
	private Rect startBounds;//开始动画的区域范围
	private float startScale;//开始的比率
	private float startScaleFinal;//结束时的比率
	
	public ZoomTutorial(View containerView,View expandedView) {
		mContainView = containerView;
		mExpandedView = expandedView;
	}
	
	/**
	 * 十分重要的一个方法，用于展示大的图片
	 * 
	 * @param thumbView
	 * @param imageResId
	 */
	public void zoomImageFromThumb(final View thumbView) {
		mThumbViewParent = (ViewGroup) thumbView.getParent();
		// If there's an animation in progress, cancel it immediately and
		// proceed with this one.
		if (mCurrentAnimator != null) {
			mCurrentAnimator.cancel();
		}

		// Calculate the starting and ending bounds for the zoomed-in image.
		// This step involves lots of math. Yay, math.
		// 计算开始和结束的边界+偏移量
		startBounds = new Rect();
		final Rect finalBounds = new Rect();// 结束的边界
		final Point globalOffset = new Point();// 目标偏移量

		// The start bounds are the global visible rectangle of the thumbnail,
		// 开始的边界是小图整体可见部分的范围
		// and the final bounds are the global visible rectangle of the container view.
		// 结束的边界是容器的边界
		// Also set the container view's offset as the origin for the bounds,
		// since that's the origin for the positioning animation properties (X, Y).
		thumbView.getGlobalVisibleRect(startBounds);
		// 这里的id，container是整个布局最外层的容器
		mContainView.getGlobalVisibleRect(finalBounds, globalOffset);

		// 开始设置偏移量
		startBounds.offset(-globalOffset.x, -globalOffset.y);
		finalBounds.offset(-globalOffset.x, -globalOffset.y);

		//设置缩放的比例和位置
		set_Center_crop(finalBounds);
		
		mExpandedView.setVisibility(View.VISIBLE);
		
		// Set the pivot point for SCALE_X and SCALE_Y transformations to the
        // top-left corner of
        // the zoomed-in view (the default is the center of the view).
		AnimatorSet animSet = new AnimatorSet();
		animSet.setDuration(1);
		animSet.play(ObjectAnimator.ofFloat(mExpandedView, "pivotX", 0f))
		.with(ObjectAnimator.ofFloat(mExpandedView, "pivotY", 0f))
		.with(ObjectAnimator.ofFloat(mExpandedView, "alpha", 1.0f));
		animSet.start();
 
        startZoomAnim(mExpandedView, startBounds, finalBounds, startScale);
		 // Upon clicking the zoomed-in image, it should zoom back down to the
		// original bounds and show the thumbnail instead of the expanded image.
		startScaleFinal = startScale;
	}
	
	/**
	 * 通过结束的边界计算开始拉伸的比例
	 * 
	 * Adjust the start bounds to be the same aspect ratio as the final bounds
	 * using the "center crop" technique. 通过 center
	 * crop算法来调整开始边界，让它和的结束边界保持同一个纵横比例，也就是长宽比 This prevents undesirable
	 * stretching during the animation.//在动画执行时保证不让图片拉伸 Also calculate the start
	 * scaling factor (the end scaling factor is always 1.0).
	 * 我们也需要计算开始的比率因子，结束比例一直是1.0.因为是将图片从小放到自己的大小。
	 */
	private void set_Center_crop(Rect finalBounds) {
		if ((float) finalBounds.width() / finalBounds.height() > (float) 
				startBounds.width() / startBounds.height()) {
			// Extend start bounds horizontally
			startScale = (float) startBounds.height() / finalBounds.height();
			float startWidth = startScale * finalBounds.width();
			float deltaWidth = (startWidth - startBounds.width()) / 2;
			startBounds.left -= deltaWidth;
			startBounds.right += deltaWidth;
		} else {
			// Extend start bounds vertically
			startScale = (float) startBounds.width() / finalBounds.width();
			float startHeight = startScale * finalBounds.height();
			float deltaHeight = (startHeight - startBounds.height()) / 2;
			startBounds.top -= deltaHeight;
			startBounds.bottom += deltaHeight;
		}
	}

	/**
	 * @param v  执行动画的view
	 * @param startBounds  开始的边界
	 * @param finalBounds  结束时的边界
	 * @param startScale  开始的拉伸比率
	 */
	public void startZoomAnim(View v, Rect startBounds, Rect finalBounds, float startScale) {
		// Construct and run the parallel animation of the four translation and
		// scale properties (X, Y, SCALE_X, and SCALE_Y).
		AnimatorSet set = new AnimatorSet();
		set.play(
			ObjectAnimator.ofFloat(v, "x", startBounds.left, finalBounds.left))
			.with(ObjectAnimator.ofFloat(v, "y", startBounds.top, finalBounds.top))
			.with(ObjectAnimator.ofFloat(v, "scaleX", startScale, 1f))
			.with(ObjectAnimator.ofFloat(v, "scaleY", startScale, 1f));
		
		set.setDuration(mAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				mCurrentAnimator = null;
				if (listener != null) {
					listener.onExpanded();
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				mCurrentAnimator = null;
				if (listener != null) {
					listener.onExpanded();
				}
			}
		});
		set.start();
		mCurrentAnimator = set;
	}

	/**
	 * 在GridView中，使用getChildAt(index)的取值，只能是当前可见区域（列表可滚动）的子项！
	 * 因为子项会进行复用。这里强制转换了下，变成了GridView，实际使用中需要进行修改
	 * 【参考】
	 * http://xie2010.blog.163.com/blog/static/211317365201402395944633/
	 * http://blog.csdn.net/you_and_me12/article/details/7271006
	 * 
	 * @param position
	 * @return 判断这个position的view是否现在显示在屏幕上，如果没有显示就返回false
	 */
	public boolean getScaleFinalBounds(int position) {
		//得到显示区域中第一个子视图的序号
		int firstPosition = ((AdapterView<?>)mThumbViewParent).getFirstVisiblePosition();
		View childView = mThumbViewParent.getChildAt(position - firstPosition);
		
		startBounds = new Rect();
		final Rect finalBounds = new Rect();
		final Point globalOffset = new Point();
		
		try {
			//通过这个计算startBounds，得到当前view的位置，从而设定偏移值
			childView.getGlobalVisibleRect(startBounds);
		} catch (Exception e) {
			return false;
		}
		mContainView.findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
		startBounds.offset(-globalOffset.x, -globalOffset.y);
		finalBounds.offset(-globalOffset.x, -globalOffset.y);
		//设置比率
		set_Center_crop(finalBounds);
		
		startScaleFinal = startScale;
		return true;
	}
	
	/**
	 * 根据position执行动画，如果这个图片在当前屏幕显示范围内，那就执行缩小。否则直接渐变
	 * @param position
	 */
	public void closeZoomAnim(int position) {
		if (mCurrentAnimator != null) {
			mCurrentAnimator.cancel();
		}
		// Animate the four positioning/sizing properties in parallel,back to their original values.
		AnimatorSet set = new AnimatorSet();
		/**
		 * 因为展开图可能是在viewpager中，所以现在显示的图片，或许并不是第一次打开的图片，这里应该考虑两点
		 * 1.改变图片缩小后回到的位置
		 * 2.如果图片缩小后回到的位置不在屏幕中，直接渐变消失
		 */
		boolean isInBound = getScaleFinalBounds(position);
		if (isInBound) {
			set.play(ObjectAnimator.ofFloat(mExpandedView, "x", startBounds.left))
				.with(ObjectAnimator.ofFloat(mExpandedView, "y", startBounds.top))
				.with(ObjectAnimator.ofFloat(mExpandedView, "scaleX", startScaleFinal))
				.with(ObjectAnimator.ofFloat(mExpandedView, "scaleY", startScaleFinal));
		} else {
			// 如果当前显示的图片不在gridview当前显示的图片中，等于越界了。这时我们就不执行缩放操作，直接渐变消失即可。
			ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mExpandedView, "alpha", 0.1f);
			set.play(alphaAnimator);
		}
		set.setDuration(mAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				mExpandedView.clearAnimation();
				mExpandedView.setVisibility(View.GONE);
				mCurrentAnimator = null;
				if (listener != null) {
					listener.onThumbed();
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				mExpandedView.clearAnimation();
				mExpandedView.setVisibility(View.GONE);
				mCurrentAnimator = null;
				if (listener != null) {
					listener.onThumbed();
				}
			}
		});
		set.start();
		mCurrentAnimator = set;
	}
	
	private OnZoomListener listener;
	
	public void setOnZoomListener(OnZoomListener l) {
		listener = l;
	}
	
	public interface OnZoomListener {
		public void onExpanded();//点击后展示大图成功后调用
		public void onThumbed();//点击后缩小回小图时调用
	}
	
}
