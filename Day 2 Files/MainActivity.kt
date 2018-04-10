package com.projects.vasudev.workshopgame

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val NUMBER_OF_ROWS=13
    val NUMBER_OF_MINES=26
    var rowLayoutArrayList:ArrayList<LinearLayout>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeGame()
    }

    private fun initializeGame() {

        //remove all child views
        gameContainerLayout.removeAllViews()
        rowLayoutArrayList= ArrayList()
        for (i in 0 until NUMBER_OF_ROWS){
            val rowLayout:LinearLayout=singleRowLayout(i)
            gameContainerLayout.addView(rowLayout)
            rowLayoutArrayList?.add(rowLayout)
        }
        setMines()
    }

    private fun getCellAtLocation(rowIndex: Int,colIndex: Int):Cell{
        val rowLayout=rowLayoutArrayList?.get(rowIndex)
        val cell=rowLayout?.getChildAt(colIndex) as Cell
        return cell
    }

    private fun setMines() {
        val random=Random()
        var i=0
        while (i<NUMBER_OF_MINES){
            val currentRow=random.nextInt(NUMBER_OF_ROWS)
            val currentCol=random.nextInt(NUMBER_OF_ROWS)
            val cell=getCellAtLocation(currentRow,currentCol)
            if(cell.isMine){
                continue
            }
            cell.scoreValue=-1
            setScore(currentRow,currentCol)
            i+=1
        }
    }

    private fun setScore(currentRow: Int, currentCol: Int) {

    }

    private fun singleRowLayout(rowIndex: Int): LinearLayout {
        val rowLayout=LinearLayout(this@MainActivity)

        for (i in 0 until NUMBER_OF_ROWS){
            val cell=generateSingleCell(rowIndex,i)
            rowLayout.addView(cell)
        }

        val rowParams=LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1f)
        rowParams.setMargins(1,1,1,1)
        rowLayout.layoutParams=rowParams
        return rowLayout
    }

    private fun generateSingleCell(rowIndex:Int,colIndex:Int):Cell{
        val cell=Cell(this@MainActivity)

        //cell properties
        cell.rowIndex=rowIndex
        cell.colIndex=colIndex
        cell.isFlagged=false
        cell.isVisted=false
        cell.scoreValue=0

        //cell UI
        cell.setBackgroundResource(R.drawable.unclicked_button)
        val cellParams=LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1f)
        cellParams.setMargins(1,0,1,0)
        cell.layoutParams=cellParams

        //handling click event on cell
        cell.setOnClickListener {
            when{

                cell.isFlagged or cell.isVisted->{

                }

                cell.isMine->{
                    cell.isVisted=true
                    Toast.makeText(this,"You Lost",Toast.LENGTH_SHORT).show()
                    onLoss()
                }
                else->{
                    cell.isVisted=true
                    when(cell.scoreValue){
                        0->{
                            cell.setBackgroundColor(Color.LTGRAY)
                            onZeroCellClicked(cell.rowIndex,cell.colIndex)
                        }
                        1->{
                            cell.setBackgroundResource(R.drawable.score_one_resource)
                        }
                        2->{
                            cell.setBackgroundResource(R.drawable.score_two_resource)
                        }
                        3->{
                            cell.setBackgroundResource(R.drawable.score_three_resource)
                        }
                        4->{
                            cell.setBackgroundResource(R.drawable.score_four_resource)
                        }
                        5->{
                            cell.setBackgroundResource(R.drawable.score_five_resource)
                        }
                        6->{
                            cell.setBackgroundResource(R.drawable.score_six_resource)
                        }
                        7->{
                            cell.setBackgroundResource(R.drawable.score_seven_resource)

                        }
                        8->{
                            cell.setBackgroundResource(R.drawable.score_eight_resource)
                        }

                    }

                    checkWin()
                }

            }
        }
        cell.setOnLongClickListener {
            when{
                cell.isVisted->{}
                cell.isFlagged->{
                    cell.isFlagged=false
                    cell.setBackgroundResource(R.drawable.unclicked_button)
                }
                !cell.isFlagged->{
                    cell.isFlagged=true
                    cell.setBackgroundResource(R.drawable.flag)
                }
            }


            true
        }

        return cell
    }

    private fun checkWin() {

    }

    private fun onZeroCellClicked(rowIndex: Int, colIndex: Int) {

    }

    private fun onLoss() {

    }
}
