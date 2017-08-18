/**
 * Created by Administrator on 2017/8/10.
 */
//Õº∆¨…œ¥´‘§¿¿    IE «”√¡À¬Àæµ°£
function previewImage1(file)
{
    var div = document.getElementById('preview1');
    if (file.files && file.files[0])
    {
        div.innerHTML ='<img class="imghead" id=imghead1>';
        var img = document.getElementById('imghead1');
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
    }
    else //ºÊ»›IE
    {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img class="imghead" id=imghead1>';
        var img = document.getElementById('imghead1');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        div.innerHTML = "<div id=divhead style='width:100%;height:100%;"+sFilter+src+"\"'></div>";
    }
}
function previewImage2(file)
{
    var div = document.getElementById('preview2');
    if (file.files && file.files[0])
    {
        div.innerHTML ='<img class="imghead" id=imghead2>';
        var img = document.getElementById('imghead2');
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
    }
    else //ºÊ»›IE
    {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img class="imghead" id=imghead2>';
        var img = document.getElementById('imghead1');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        div.innerHTML = "<div id=divhead style='width:100%;height:100%;"+sFilter+src+"\"'></div>";
    }
}
function previewImage3(file)
{
    var div = document.getElementById('preview3');
    if (file.files && file.files[0])
    {
        div.innerHTML ='<img class="imghead" id=imghead3>';
        var img = document.getElementById('imghead3');
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
    }
    else //ºÊ»›IE
    {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img class="imghead" id=imghead3>';
        var img = document.getElementById('imghead1');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        div.innerHTML = "<div id=divhead style='width:100%;height:100%;"+sFilter+src+"\"'></div>";
    }
}