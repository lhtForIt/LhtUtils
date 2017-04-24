package com.lht.utils.lhtutils.Other;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lht.utils.lhtutils.Activity.AliPlayTest;
import com.lht.utils.lhtutils.Pay.PayResult;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by lht on 2017/3/8.
 */

public class LhtConfig {


    public static String GUZHU_INFO = "http://app.680.com/api/v3/index_gz_data.ashx";
    public static String HTTP_URL_ALIPAY_notify_url = "http://app.680.com/api/alipay_server.aspx";

    public static String PARTNER = AliPlayTest.PARTNER;
    public static String SELLER = AliPlayTest.SELLER;
    public static String RSA_PRIVATE =AliPlayTest.private_key;
    public static String RSA_PUBLIC =AliPlayTest.public_key;


    public static Handler getHander(final Context context, final String num) {
        final int SDK_PAY_FLAG = 1;
        final int SDK_CHECK_FLAG = 2;

        return new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


                switch (msg.what) {
                    case SDK_PAY_FLAG: {
                        PayResult payResult = new PayResult((String) msg.obj);
                        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                        String resultInfo = payResult.getResult();
                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "9000")) {
                            Toast.makeText(context, "支付成功",
                                    Toast.LENGTH_SHORT).show();
                            /**
                             * float price=1.2;
                             DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                             String p=decimalFomat.format(price);//format 返回的是字符串
                             */
//                            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
//                            float f = Float.valueOf(MyApplication.getInstance().userBean.getBalance()) + Float.valueOf(num);
//                            MyApplication.getInstance().userBean.setBalance(decimalFormat.format(f));
//                            context.startActivity(new Intent(context, MainActivity.class));
                        } else {
                            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            if (TextUtils.equals(resultStatus, "8000")) {
                                Toast.makeText(context, "支付结果确认中",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                                Toast.makeText(context, "支付失败",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    }
                    case SDK_CHECK_FLAG: {
                        Toast.makeText(context, "检查结果为：" + msg.obj,
                                Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:

                        break;
                }
            }
        };
    }


    public static void showShare(Context con) {
        ShareSDK.initSDK(con);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("我是一条分享");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是XXX分享de具体内容");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("时间财富");
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(con);
    }


    public static String getHouse_CID(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String driveId = null;
        try {
            driveId = mTelephonyManager.getDeviceId();
            return driveId;
        } catch (Exception e) {
            Log.d("lht", "=====================Exception:" + e.toString());
        }
        return null;
    }







}
