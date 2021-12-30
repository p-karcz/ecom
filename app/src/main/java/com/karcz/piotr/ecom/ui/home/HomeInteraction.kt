package com.karcz.piotr.ecom.ui.home

sealed class HomeInteraction {
    object HomeMenuItemClicked : HomeInteraction()
    object CartMenuItemClicked : HomeInteraction()
    object AccountMenuItemClicked : HomeInteraction()
}
