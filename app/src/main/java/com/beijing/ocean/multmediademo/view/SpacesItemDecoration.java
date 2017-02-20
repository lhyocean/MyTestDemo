package com.beijing.ocean.multmediademo.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
  private int space;
  private int lspace;
  private int tspace;
  private int rspace;
  private int bspace;



  public SpacesItemDecoration(int space) {
    this.space = space;
  }
  public SpacesItemDecoration(int lspace, int tspace, int rspace, int bspace) {
    this.lspace = lspace;
    this.tspace = tspace;
    this.rspace = rspace;
    this.bspace = bspace;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view,
                             RecyclerView parent, RecyclerView.State state) {

    if(space!=0){
      outRect.left = space;
      outRect.right = space;
      outRect.bottom = space;

      // Add top margin only for the first item to avoid double space between items
      //if(parent.getChildPosition(view) == 0)
      outRect.top = space;
    }else{

      outRect.left = lspace;
      outRect.right = rspace;
      outRect.bottom = bspace;

      // Add top margin only for the first item to avoid double space between items
      //if(parent.getChildPosition(view) == 0)
      outRect.top = tspace;
    }

  }

}