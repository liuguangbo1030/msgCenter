<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>企保招招-邮件通知</title>
    <link rel="icon" sizes="16x16 32x32" mask="" href="http://static01.qibaozz.com/index3.0/img/qibao.png">
</head>
<body>
<div style="width: 750px;padding: 13px 60px 13px 60px;background-color: #524ae7;box-sizing: border-box;margin: 0 auto;">
    <img src="http://static01.qibaozz.com/index3.0/img/head-logo.png" />
</div>
<div style="width: 750px;padding: 0 60px 50px 60px;font-size: 16px;box-sizing: border-box;margin: 0 auto;background:#f8f7fe;">
    <div style="color: #35313f;line-height: 30px;">
        <p style="font-size: 18px;margin-top:0;padding-top: 30px;margin-bottom: 23px;">欢迎来到企保招招</p>
        <p>亲爱的用户<#if name??>，${name}</#if>：</p>
        <p style="height: 200px;margin-top: -15px;margin-bottom: 46px;">
            ${content}
        </p>
        <p style="text-align: right;">企保招招团队</p>
        <div style="position: relative;height: 44px;">
            <p style="line-height: 24px;width: 100px;position: absolute;top: -32px;right: 3px;">${date}</p>
        </div>
    </div>
    <div style="color: #b1afbb;border-top: 1px solid #e9e9f4;border-bottom: 1px solid #e9e9f4;">
        <p style="line-height: 30px;">
            本邮件是用户注册企保招招后系统自动发出，如果你并未注册企保招招，可能是因为其他用户误输入了你的邮箱地址而使你收到这封邮件，你可以忽略此邮件。</p>
    </div>
    <p style="color: #727689;">如有任何问题，可以与我们联系，我们将尽快为你解答。</p>
    <p style="color: #727689;">
        官网：http://www.qibaozz.com
    </p>
    <p style="color: #727689;line-height: 24px;">Email :
        <a style="display: inline-block;border-bottom: 1px dashed #727689;">service@qibaozz.com</a>　
        电话 :<a style="display: inline-block;border-bottom: 1px dashed #727689;">400-009-7855</a>
    </p>
</div>
</body>
</html>