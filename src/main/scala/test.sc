import org.apache.spark.sql.{DataFrame, SparkSession, Column}
import org.apache.spark.sql.functions._


val spark = SparkSession.builder
  .master("local[*]")
  .appName("Spark Word Count")
  .config("spark.ui.enabled", false)
  .config("spark.ui.showConsoleProgress", false)
  .getOrCreate()


val file = spark.read.option("sep", "\t").option("header", "true").csv("/Users/bryce/Documents/scala_work/smite/src/main/data/matches_6_11.tsv")

// Get the different type of games available
// val uniqueMapGame = file.groupBy("name").count().orderBy().show()
// val distinctValuesDF = file.select(file("age")).distinct

val sample = file.sample(true, 1D*5000/file.count)
sample.coalesce(1).write.option("delimiter", "\t").csv("/Users/bryce/Documents/scala_work/smite/src/main/data/sampleData")


// junk

//def selectColumns(columnArray: Array[String], df: DataFrame, drop: Boolean = false): DataFrame = {
//  def bool(colName: String): Boolean = if (drop) !columnArray.contains(colName) else columnArray.contains(colName)
//  df.select(df.columns.filter(colName => bool(colName)).map(colName => new Column(colName)): _*)
//}
//def getUserData(playerToSelect: String, df: DataFrame): DataFrame = {
//  df.filter(df("playerName") === playerToSelect)
//}
////
//val user = getUserData("Kalistarter", file)
//println(user.show())
//val pickedDf = selectColumns(Array("playerName"), file)
//println(pickedDf.show())
//
//val usefulColumns = file.select("playerName", "Deaths", "GodId", "Reference_Name", "Damage_Done_Magical", "Damage_Done_Physical", "Damage_Player", "Damage_Taken", "Gold_Earned", "Gold_Per_Minute", "Healing", "Item_Purch_1", "Item_Purch_2","Item_Purch_3","Item_Purch_4","Item_Purch_5","Item_Purch_6" )
//println(usefulColumns.show())
//
//
//val numberColumns = Array("Deaths", "Damage_Done_Magical", "Damage_Done_Physical", "Damage_Player", "Damage_Taken", "Gold_Earned", "Gold_Per_Minute", "Healing")
//val numberColumnsData = selectColumns(numberColumns, usefulColumns)
//
//
//val statDescription = selectColumns(numberColumns, usefulColumns).describe()
//statDescription.show()
//
//
//val healingGods = Array("Aphrodite", "Hel", "Sylvanus", "Terra", "Ra", "Chang'e", "Guan Yu", "Yemoja", "Baron Samedi", "Horus")
//val healingGodsData = file.filter(file("Reference_Name") isin healingGods)

// Healing done by healing gods




