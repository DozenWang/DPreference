### DPreference
If you have used SharePreference's MULTI_PROCESS_MODE, you may know that it's not reliable.

See the documentation of the [`SharedPreferences`](http://developer.android.com/reference/android/content/SharedPreferences.html) you might have seen one of these warnings:
>Note: currently this class does not support use across multiple processes. This will be added later.

Google even deprecated the multiprocess support because it never worked relieable

[![](https://cloud.githubusercontent.com/assets/1096485/9793296/110575d2-57e5-11e5-9728-34d3597771b8.png)](http://developer.android.com/reference/android/content/Context.html#MODE_MULTI_PROCESS)

DPreference is a way solving this problem powered by a ContentProvider. It works in multiple processed mode safely. You can declare your provider in a remote process, if you don't declare it in AndroidManifest.xml, the default is main process.

####Features
- **works multiprocess safely**
- the same api like SharePreference
- compat old app versions, don't need to upgrade.
  DPreference is just a wrapper of original sharepreference with contentprovider.

####Usage
```
       DPreference dPreference = new DPreference(context, "default");
       dPreference.setPrefString( "key", "value");

```

