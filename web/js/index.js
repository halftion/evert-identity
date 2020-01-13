/*
* @Author: wujingchuan
* @Date:   2018-04-21 10:28:43
* @Last Modified by:   wujingchuan
* @Last Modified time: 2018-04-26 19:35:18
*/
window.onload=function(){
    /*轮播*/
    var aBox=document.getElementById("boxs");
    var n=aBox.getElementsByTagName("div").length;
    var Num=1;/*总辅助计时用*/
    var timer=setInterval(pass,3500);
    function pass(){
        a=(-Num%n)*1920;/*不点击时辅助计时用*/
        document.getElementById("boxs").style.left=a+"px";
        Num=Num+1;
    }
    timer;
    /*鼠标移上后停止，移开重新开始*/
    aBox.onmouseover=function(){
        clearInterval(timer);
    }
    aBox.onmouseout=function(){
        clearInterval(timer);
        timer=setInterval(pass,3500);
    }
    /*用下方小圆点控制轮播*/
    var Lis = document.getElementsByTagName("li");
    var aLi=new Array()
    for(i = 0;i < Lis.length;i++){
        aLi[i] = Lis[i].id;
    }
    /*获取li id完毕*/
    document.getElementById("li1").onclick=function(){
        clearInterval(timer);
        Num=1;
        document.getElementById("boxs").style.left=0+"px"
        timer=setTimeout(pass,2500);
    }
    document.getElementById("li2").onclick=function(){
        clearInterval(timer);
        Num=2;
        document.getElementById("boxs").style.left=-1920+"px"
        timer=setTimeout(pass,2500);
    }
    document.getElementById("li3").onclick=function(){
        clearInterval(timer);
        Num=3;
        document.getElementById("boxs").style.left=-3840+"px"
        timer=setTimeout(pass,2500);
    }
    /*点击后清除计时器 重新设置计时器 重新计时*/
    //计时器完毕
}
function toLogon(){
    alert("请登陆后再进行操作。");
}