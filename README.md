# 插件使用介绍

#  插件接入  
`api 'com.github.tianhaifeng1:module_common:1.0.5'`  

## 1.扫码接入
调用代码
`ModuleUtils.startScan(activity, 100) //参数为上下文，requestCode`  
随后在onActivityResult中根据对应的requestCode获取数据  

## 2.图片，音视频录入
参考*app.main.com.example.qualityshield.activity.photo.DemoActivity*

## 3.wifi相关  
```
if (NetworkUtils.isWifiConnected()) {   //判断wifi是否连接
    String mac = MacUtil.getMac(activity);  //获取MAC地址
}
```  

## 4.NFC
```
NfcUtils nfcUtils = new NfcUtils(this);  //初始化  

//在onResume中开启前台调度
@Override
protected void onResume() {
    super.onResume();
    //设定intentfilter和tech-list。如果两个都为null就代表优先接收任何形式的TAG action。也就是说系统会主动发TAG intent。
    if (NfcUtils.mNfcAdapter != null) {
        NfcUtils.mNfcAdapter.enableForegroundDispatch(this, NfcUtils.mPendingIntent, NfcUtils.mIntentFilter, NfcUtils.mTechList);
    }
}


//在onNewIntent中处理由NFC设备传递过来的intent
@Override
protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    Log.e(TAG, "--------------NFC-------------" );
    processIntent(intent);
}

//  这块的processIntent() 就是处理卡中数据的方法
public void processIntent(Intent intent) {
    Parcelable[] rawmsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
    NdefMessage msg = (NdefMessage) rawmsgs[0];
    NdefRecord[] records = msg.getRecords();
    String resultStr = new String(records[0].getPayload());
    // 返回的是NFC检查到卡中的数据
    Log.e(TAG, "processIntent: "+resultStr );
    mBinding.nfcText.setText("NFC卡中数据为："+resultStr);
    try {
        // 检测卡的id
        String id = NfcUtils.readNFCId(intent);
        Log.e(TAG, "processIntent--id: "+id );
        // NfcUtils中获取卡中数据的方法
        String result = NfcUtils.readNFCFromTag(intent);
        Log.e(TAG, "processIntent--result: "+result );
        // 往卡中写数据
        ToastUtils.showLong(result);
        String data = "this.is.write";
        NfcUtils.writeNFCToTag(data,intent);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (FormatException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Override
protected void onPause() {
    super.onPause();
    if (NfcUtils.mNfcAdapter != null) {
        NfcUtils.mNfcAdapter.disableForegroundDispatch(this);
    }
}

@Override
protected void onDestroy() {
    super.onDestroy();
    NfcUtils.mNfcAdapter = null;
}
```

## 5.H5  

```
Intent intent = new Intent(activity, WebActivity.class);
                intent.putExtra("url","https://www.baidu.com");
                StartActivity(intent)
```

## 6.定位相关

```
//onResume中开启定位服务
@Override
@SuppressLint("MissingPermission")
protected void onResume() {
    super.onResume();
    ModuleUtils.startLocation(this, locationListener);
}

//页面关闭停止服务
@Override
protected void onDestroy() {
    ModuleUtils.stopLocation(locationListener);
    super.onDestroy();
}

//然后开启位置监听
locationListener = location -> mBinding.locationText.setText("经度："+location.getLongitude()+",纬度："+location.getLatitude());  
```

##等待更新