⟳ lxglifecycle [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.stephanenicolas.lxglifecycle/lxglifecycle-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.stephanenicolas.lxglifecycle/lxglifecycle-plugin)
============

***Logs all lifecycle methods of annotated activities, fragments, views, etc. on Android.***

###Usage

Inside your `build.gradle` file, add : 

```groovy
apply plugin: 'lxglifecycle'
```

And now, annotate every `Activity` you want to see its life cycle logged in logcat : 

```java
@LxgLifeCycle
public class MainActivity extends Activity {
...
}
```

You will see something like this in logcat : 

```bash
> adb logcat | grep ⟳

D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onCreate
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onApplyThemeResource
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onWindowAttributesChanged
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onWindowAttributesChanged
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onWindowAttributesChanged
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onContentChanged
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onAttachFragment
D/LxgLifeCycle( 4640): MainActivity$MainFragment [1384183528] ⟳ onCreate
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onStart
D/LxgLifeCycle( 4640): MainActivity$MainFragment [1384183528] ⟳ onStart
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onTitleChanged
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onPostCreate
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onResume
D/LxgLifeCycle( 4640): MainActivity$MainFragment [1384183528] ⟳ onResume
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onPostResume
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onAttachedToWindow
D/LxgLifeCycle( 4640): MainActivity [1384162984] ⟳ onWindowFocusChanged
```

Extra features : 
* release builds won't log anything, indeed the app is not modified at all in release builds.
* this plugin will add only a few more bytes to your app : log statements, and the annotation class, nothing more.

###Example

You will find an example app using LxgLifeCycle in the repo.

###How does it work ?

Thanks to 
* [morpheus](https://github.com/stephanenicolas/morpheus), byte code weaver for android.
* [AfterBurner](https://github.com/stephanenicolas/afterburner), byte code weaving swiss army knife for Android.

### Related projects 

On the same principle of byte code weaving : 

* [InjectView](https://github.com/stephanenicolas/injectview)
* [InjectResource](https://github.com/stephanenicolas/injectresource)
* [InjectExtra](https://github.com/stephanenicolas/injectextra)
* [Hugo](https://github.com/jakewharton/hugo)

###TODO
* [ ] add tests

### CI 

[![Travis Build](https://travis-ci.org/stephanenicolas/lxglifecycle.svg?branch=master)](https://travis-ci.org/stephanenicolas/lxglifecycle)
[![Coverage Status](https://img.shields.io/coveralls/stephanenicolas/lxglifecycle.svg)](https://coveralls.io/r/stephanenicolas/lxglifecycle)

License
-------

	Copyright (C) 2014 Stéphane NICOLAS

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	     http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

