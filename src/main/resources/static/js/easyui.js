/**
 * Created by dell-pc on 2016/4/21.
 */
function showInfoMessage(text){
    $.messager.show({
        title : '消息提示',
        msg : text,
        timeout : 2000,
        showType : 'slide'
    });
}
