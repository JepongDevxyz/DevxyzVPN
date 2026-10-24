package com.slipkprojects.sockshttp.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemTouch implements OnItemTouchListener {

	private ClickListener clickListener;
    private GestureDetector gestureDetector;

	public class SimpleOnGesture extends SimpleOnGestureListener {
        private final RecyclerItemTouch this$0;
        private final ClickListener clickListener;
        private final RecyclerView recyclerView;


		public SimpleOnGesture(RecyclerItemTouch recyclerTouchListener, RecyclerView recyclerView, ClickListener clickListener) {
            SimpleOnGestureListener simpleOnGestureListener = this;
            this.this$0 = recyclerTouchListener;
            this.recyclerView = recyclerView;
            this.clickListener = clickListener;
        }
        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            View findChildViewUnder = this.recyclerView.findChildViewUnder(motionEvent2.getX(), motionEvent2.getY());
            if (findChildViewUnder != null && clickListener != null) {
                clickListener.onClick(findChildViewUnder, recyclerView.getChildPosition(findChildViewUnder));
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            MotionEvent motionEvent2 = motionEvent;
            View findChildViewUnder = this.recyclerView.findChildViewUnder(motionEvent2.getX(), motionEvent2.getY());
            if (findChildViewUnder != null && clickListener != null) {
                clickListener.onLongClick(findChildViewUnder, recyclerView.getChildPosition(findChildViewUnder));
            }
        }
    }

    public interface ClickListener {
        void onClick(View view, int i);

        void onLongClick(View view, int i);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent2.getX(), motionEvent2.getY());
        if (findChildViewUnder != null && clickListener != null) {
            clickListener.onClick(findChildViewUnder, recyclerView.getChildPosition(findChildViewUnder));
        }
    }

    public RecyclerItemTouch(Context context, RecyclerView recyclerView, ClickListener clickListener) {
        this.clickListener = clickListener;
        SimpleOnGesture SG = new SimpleOnGesture(this, recyclerView, clickListener);
        GestureDetector gestureDetector = new GestureDetector(context, SG);
        this.gestureDetector = gestureDetector;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (!(findChildViewUnder == null || clickListener == null || !gestureDetector.onTouchEvent(motionEvent))) {
            clickListener.onClick(findChildViewUnder, recyclerView.getChildPosition(findChildViewUnder));
        }
        return false;
    }
}

