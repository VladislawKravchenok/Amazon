object Main {

    lateinit var consumerList: List<String>

    @JvmStatic
    fun main(args: Array<String>) {
        //список кодов для Боба на каждый день и комбинации к ним
        val map: HashMap<Int, List<List<String>>> = HashMap()
        map[233] = listOf(listOf("banana", "orange"), listOf("anything", "anything"), listOf("apple", "apple"))
        map[432] = listOf(listOf("orange", "banana"), listOf("anything", "anything"), listOf("apple", "apple"), listOf("banana"))
        map[411] = listOf(listOf("orange", "orange", "orange"), listOf("anything", "anything", "potatos", "apple"))

        val compileList: List<List<String>> = map[411]!!//аргумент = Код боба

        //Покупки человекав
        consumerList = listOf("banana", "orange", "apple", "orange","apple", "apple")
        getResult(compileList, consumerList)
        consumerList = listOf("orange", "banana", "anything", "potatos", "apple", "apple", "banana","potatos","potatos")
        getResult(compileList, consumerList)
        consumerList = listOf("orange", "orange", "orange","fish", "apple", "potatos", "apple")
        getResult(compileList, consumerList)
        consumerList = listOf("orange", "orange", "orange")
        getResult(compileList, consumerList)
    }

    private fun getResult(fruitGroup: List<List<String>>, consumerList: List<String>) {
        if (checkWin(fruitGroup, consumerList) == 1) {
            println("You are Winner!")
        } else {
            println("Thank you for buying, bye-bye")
        }
    }

    private fun checkWin(fruitGroup: List<List<String>>, consumerList: List<String>): Int {
        var total = 0 //элементов в группе
        val i = consumerList.size //всего покупок клиента

        for (array in fruitGroup) total += array.size

        if (total > i) return 0

        var listsInList = 0
        var lastPosition = 0
        while (listsInList < total) {
            lastPosition = takeMeFor(fruitGroup[listsInList], consumerList, lastPosition)
            if (lastPosition == 0) {
                break
            }
            total -= fruitGroup[listsInList].size
            listsInList++
        }

        if (total <= 0) {
            return 1
        }
        return 0
    }

    // берёт лист покупок и сравнивает его с одним листом из списка боба, фиксируя индекс
    private fun takeMeFor(fruitGroup: List<String>, consumerList: List<String>, lastPosition: Int): Int {
        var total: Int = lastPosition
        var totalSimilarities = 0

        for (value in fruitGroup) {
            while (total < consumerList.size) {
                if (consumerList[total] == value || value == "anything"){
                    totalSimilarities++
                    total++
                    break
                }
                total++
            }
        }
        if (totalSimilarities != fruitGroup.size) {
            return 0
        }
        return total

    }


}