package de.marmaro.krt.ffupdater.app

import de.marmaro.krt.ffupdater.app.impl.*

enum class App(appDetail: BaseApp) {
    FIREFOX_RELEASE(FirefoxRelease()),
    FIREFOX_BETA(FirefoxBeta()),
    FIREFOX_NIGHTLY(FirefoxNightly()),
    FIREFOX_FOCUS(FirefoxFocus()),
    FIREFOX_KLAR(FirefoxKlar()),
    LOCKWISE(Lockwise()),
    BRAVE(Brave()),
    ICERAVEN(Iceraven()),
    BROMITE(Bromite()),
    VIVALDI(Vivaldi()),
    UNGOOGLED_CHROMIUM(UngoogledChromium());

    val detail: BaseApp = appDetail
}