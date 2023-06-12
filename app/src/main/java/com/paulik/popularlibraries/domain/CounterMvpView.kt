package com.paulik.popularlibraries.domain

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @AddToEndSingle - стратегия
 * Анатацию можно разместить над интерфейсом если все функции одинаковой стратегии.
 * @AddToEndStrategy – добавит пришедшую команду в конец очереди. Используется по умолчанию
 * @AddToEndSingleStrategy – добавит пришедшую команду в конец очереди команд. Причём, если
 * команда такого типа уже есть в очереди, то уже существующая будет удалена
 * @SingleStateStrategy – очистит всю очередь команд, после чего добавит себя в неё
 * @SkipStrategy – команда не будет добавлена в очередь, и никак не изменит очередь
 */

interface CounterMvpView : MvpView {
    @AddToEndSingle
    fun setOneButtonText(text: String)

    @AddToEndSingle
    fun setTwoButtonText(text: String)

    @AddToEndSingle
    fun setThreeButtonText(text: String)
}