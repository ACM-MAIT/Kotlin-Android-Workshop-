package com.projects.vasudev.workshopgame;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Vasudev on 4/10/2018.
 */

public class Cell extends android.support.v7.widget.AppCompatButton {

    private int rowIndex;
    private int colIndex;
    private boolean isVisted;
    private boolean isFlagged;
    private int scoreValue;

    public Cell(Context context) {
        super(context);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public boolean isVisted() {
        return isVisted;
    }

    public void setVisted(boolean visted) {
        isVisted = visted;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public boolean isMine(){
        return scoreValue==-1;
    }
}
