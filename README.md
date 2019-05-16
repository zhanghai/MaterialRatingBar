# MaterialRatingBar

![Icon](sample/src/main/launcher_icon-web.png)

Material Design `RatingBar` with better appearance, compatible with Android 3.0+.

## Why MaterialRatingBar?

- Consistent appearance on Android 3.0+.
- Extends framework `RatingBar`.
- Get the 2dp star border background as in Material Icons and Google apps.
- Correct custom tinting across platforms.
- Able to render correctly when `layout_width` is set to `match_parent`, as in Google Play Store.
- Able to scale correctly when `layout_height` is set to values other than 16dp, 36dp and 48dp.
- Able to display ratings such as `4.3` correctly, which will be filled to `4.5` by framework's incorrect implementation.
- Avoid framework's sunken half star visual glitch.
- Used as a drop-in replacement for framework `RatingBar`.

## Preview

<a href="https://play.google.com/store/apps/details?id=me.zhanghai.android.materialratingbar.sample" target="_blank"><img alt="Google Play" height="90" src="https://play.google.com/intl/en_US/badges/images/generic/en_badge_web_generic.png"/></a>

[Sample APK](//github.com/zhanghai/MaterialRatingBar/releases/download/v1.3.2/sample-release.apk)

![Sample app](screenshot/sample_app.jpg)

## Integration

Gradle:

```gradle
implementation 'me.zhanghai.android.materialratingbar:library:1.3.2'
```

## Usage

Simply replace your `RatingBar` with `MaterialRatingBar`, and remember to apply a corresponding style for correct behavior.

For example, to create a normal `MaterialRatingBar`:

```xml
<me.zhanghai.android.materialratingbar.MaterialRatingBar
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    style="@style/Widget.MaterialRatingBar.RatingBar" />
```

In order to make your `RatingBar` take the correct and consistent size on all versions, you will always need to use one of the styles from this library. The trick inside it is `android:minHeight` and `android:maxHeight` that controls the drawable height.

You can checkout more small or indicator variants in [styles.xml](library/src/main/res/values/styles.xml).

You can use `app:mrb_fillBackgroundStars` to control whether background stars are filled, otherwise it defaults to the same value as `android:isIndicator` which is the behavior of Google apps.

8 tint-related attributes such as `app:mrb_progressTint` and `app:mrb_progressTintMode` are also supported so that they can control the tinting of rating drawables. The default tint color is `?colorControlActivated`, and the default tint mode is `src_in`.

An `OnRatingChangeListener` interface is also added to `MaterialRatingBar`, which enables callback while user is dragging, just as the listener in `SeekBar`.

For a detailed example, you can refer to the [sample app's layout](//github.com/zhanghai/MaterialRatingBar/blob/master/sample/src/main/res/layout/main_activity.xml), where you can find examples such as tinting and wide layout.

## Design

### Filled star or star border

The framework's `RatingBar` uses filling stars with grey color as track, however as per the Material Icons site, star border icons are given.

![Material Icons](screenshot/material_icons.png)

And as for the Google Play Store and Google I/O app, they are both using the star borders as track.

![Google Play Store](screenshot/google_play_store.jpg) ![Google I/O](screenshot/google_io.jpg)

With Google's design practice and aesthetic considerations taken into account, I decided to use the star border style.

### Star size

Google Play Store has stars of optical size 24dp, while Google I/O app and framework `Widget.Material.RatingBar.Indicator` have stars of size 36dp (which are of optical size 30dp). (The framework's default size of 64dp is ridiculously large and thus not taken into consideration.)

Also noticing that the Material Icons site gives icons of 24dp (optical 20dp) and 36dp (optical 30dp), I decided to stick to the 36dp approach which is also visually pleasant.

### Star border width

The ring for radio button in Material Design has a width of 2dp, and with experiments on other border widths, I decided to adopt the 2dp border width.

The star border icon is drawn with the help of Inkscape, by downloading the star icon SVG from Material Icons, duplicating the outer border path of the star, setting a stroke of 4dp, running stroke to path on it, extracting the inner border path, and finally combining this path and the original outer border path.

### Wide layout

Framework `RatingBar` gives erroneous rendering for `RatingBar` when `layout_width` is set to `match_parent` by tiling the stars without any gap. Since Google Play Store employed the wide design, I implemented it inside this library as well, so that `match_parent` will work properly for `MaterialRatingBar`.

### Dragging

Google Play Store and Google I/O app both used an implementation other than `RatingBar`, which means dragging on the bar across stars won't work (it is the functionality of `AbsSeekBar`). I think this is a handy way of interaction for users, and it enables the setting of 0 star which can be useful if you want to enable users to reset their rating to unrated.

## License

    Copyright 2016 Zhang Hai

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
