
/**获得当前日期**/
    function showtime() {
    var today, year, month, day, hour, minute, second, weekday, strDate;
    today = new Date();
    weekday = today.getDay();
    switch (weekday) {
        case 0:
        {
            strDate = "星期日";
        }
            break;
        case 1:
        {
            strDate = "星期一";
        }
            break;
        case 2:
        {
            strDate = "星期二";
        }
            break;
        case 3:
        {
            strDate = "星期三";
        }
            break;
        case 4:
        {
            strDate = "星期四";
        }
            break;
        case 5:
        {
            strDate = "星期五";
        }
            break;
        case 6:
        {
            strDate = "星期六";
        }
            break;
    }
    year = today.getFullYear();
    month = today.getMonth() + 1;
    day = today.getDate();
    hour = today.getHours();
    minute = today.getMinutes();
    second = today.getSeconds();
    if (("" + month).length < 2) month = "0" + month;
    if (("" + day).length < 2) day = "0" + day;
    if (("" + hour).length < 2) hour = "0" + hour;
    if (("" + minute).length < 2) minute = "0" + minute;
    if (("" + second).length < 2) second = "0" + second;
    //window.status = strDate + " " + year + "-"+ month + "-"+ day + " "+ hour + ":"+ minute + ":" + second;

    document.getElementById('time').innerHTML = year + "年" + month + "月" + day + "日" + "  " + strDate + "  " + hour + ":" + minute + ":" + second; //显示时间
    setTimeout("showtime()", "1000");
}
