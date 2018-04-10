# Workshop Day 2

### Activity
Single screen on android phone. An activity is a single, focused thing that the user can do. Almost all activities interact with the user, so the Activity class takes care of creating a window for you in which you can place your UI.

### onCreate(Bundle)
Kind of the main function of an activity. This is where you initialize your activity and define its UI.

### View Class
All UI components inherit properties of this class
View is the base class for widgets, which are used to create interactive UI components (buttons, text fields, etc.).
We will be using setOnClickListener method from View Class

### Access UI elements by ID
```kotlin
//remove all previous child views of the Layout
gameContainerLayout.removeAllViews()
```

### Linear Layout
A UI widget that is used as a container that aligns all its children in same direction (Horizontally or Vertically)

### Basic Logic in Game Dev
- There would be a parent Linear Layout in vertical orientation.
- It would be having N Linear Layouts as its children
- Each of thes child Linear Layout would be having N Cells in horizontal orientation

### Game Properties

```kotlin
	private val NUMBER_OF_ROWS=13   //(N)
    private val NUMBER_OF_MINES=26
    private val ROW_INDEXES = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
    private val COL_INDEXES = intArrayOf(1, 1, 1, 0, 0, -1, -1, -1)

    lateinit var mRowLayoutList:ArrayList<LinearLayout>

```

### Making a Single Cell

The cell we will be using would be a button but with some special properties. 

#### Properties:
- rowIndex(x-coordinate)
- colIndex (y-coordinate)
- scoreValue
-  isVisited (know button has been clicked or not)
- isFlagged (know button has been flagged or not)
- isMine(scoreValue==-1)

```
	    cell.rowIndex=rowIndex
        cell.colIndex=i
        cell.isFlagged=false
        cell.isVisited=false
        cell.scoreValue=0
```

#### UI Properties
```kotlin
//LinearLayout.LayoutParams(width, height,weight)
	//explaination later
	val cellParams=LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1f)
   cellParams.setMargins(1,0,1,0)
    cell.background=ContextCompat.getDrawable(this@GameActivity,R.drawable.unclicked_button)
cell.layoutParams=cellParams
```

#### Click Listener
Handle events whether clicked cell is a mine or already has been clicked or has some score value

```kotlin
cell.setOnClickListener {

                when{
					 //cell flagged or visited
                    cell.isVisited or cell.isFlagged->{

                        //no handling required

                    }
					//cell is mine or not
                    cell.isMine->{

                        //mark isVisited true as first thing
                        cell.isVisited=true

                        //change background image
                        cell.setBackgroundResource(R.drawable.clicked_mine)

                        //handle on Loss
                        // implement it later
                        onLoss(cell.rowIndex,cell.colIndex)

                    }
					
					//score button clicked
                    else->{

                        //mark isVisited true as first thing
                        cell.isVisited=true

                        when(cell.scoreValue){

                            0->{
                                cell.setBackgroundColor(Color.LTGRAY)

                                //score zero called
                                scoreZeroCellCalled(cell.rowIndex,cell.colIndex)
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

						// implement it later
                        checkWin()
                    }

                }
            }
```

### Handle Zero Score Cell Clicked

```kotlin
	for (i in 0 until ROW_INDEXES.size){
            val currentRow=rowIndex+ROW_INDEXES[i]
            val currentCol=colIndex+COL_INDEXES[i]
            if((currentRow < 0) or (currentRow >= NUMBER_OF_ROWS) or (currentCol < 0) or (currentCol >= NUMBER_OF_ROWS)){
                continue
            }
            val currentCell=getCellAtCurrentLocation(currentRow,currentCol)
            //check if current cell is a mine or not
            if (currentCell.isMine) {
                continue
            }

            //check if current cell is already clicked or not
            if (currentCell.isVisited) {
                continue
            }
            currentCell.callOnClick()

        }

```

#### LongClick Listener

Toggle Flags on given cell

```kotlin
 		cell.setOnLongClickListener(object :View.OnLongClickListener{

                override fun onLongClick(v: View?): Boolean {

                    when{
					
						//cannot flag already visited cell
                        cell.isVisited->{}
						
						//if cell is flagged , unflag it
                        cell.isFlagged->{
                            cell.isFlagged=false
                            cell.setBackgroundResource(R.drawable.unclicked_button)
                        }
						
						//if cell is unflagged , flag it
                        !cell.isFlagged->{
                            cell.isFlagged=true
                            cell.setBackgroundResource(R.drawable.flag)
                        }
                    }
                    return true
                }
            })
```

### Making a Single Row Layout

A Linear Layput containing N cells placed horizontally to eachother

```kotlin
		val rowLayout=LinearLayout(this@GameActivity)

        for (i in 0 until NUMBER_OF_ROWS){
            val cell=generateSingleCell(rowIndex,i)

            rowLayout.addView(cell)
        }
		
		//setting appearance of row
        val rowParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f)
        rowParams.setMargins(1, 1, 1, 1)
        rowLayout.layoutParams = rowParams

```

### Adding it alltogether
```kotin
		gameContainerLayout.removeAllViews()
        mRowLayoutList= ArrayList()

        //until for loop
        for (i in 0 until NUMBER_OF_ROWS){
            val currentRow=singleRowLayout(i)
            mRowLayoutList.add(currentRow)
            gameContainerLayout.addView(currentRow)
        }

        setMines(mRowLayoutList) 

```

### Set Mines

```
	 private fun setMines(mRowLayoutList: ArrayList<LinearLayout>) {
        val random=Random()
        var i=0
        while (i<NUMBER_OF_MINES){
            val mineRow = random.nextInt(NUMBER_OF_ROWS)
            val mineCol = random.nextInt(NUMBER_OF_ROWS)

            val currentCell=getCellAtCurrentLocation(mineRow,mineCol)
            if(currentCell.isMine){
                continue
            }
            currentCell.scoreValue=-1
            setScores(mineRow,mineCol)

            i+=1
        }
    }
```
### Set Score
```
	private fun setScores(mineRow: Int, mineCol: Int) {
        for(i in 0 until  ROW_INDEXES.size){

            val currentRow = mineRow + ROW_INDEXES[i]
            val currentCol = mineCol + COL_INDEXES[i]

            if((currentRow < 0) or (currentRow >= NUMBER_OF_ROWS) or (currentCol < 0) or (currentCol >= NUMBER_OF_ROWS)){
                continue
            }

            val currentCell=getCellAtCurrentLocation(currentRow,currentCol)
            if(!currentCell.isMine){
                currentCell.scoreValue+=1
            }
        }
    }
```
