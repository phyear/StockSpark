function autoScreen() {
    var bodyHeight=$(document.body).height();
    if(bodyHeight<625){
        var tops=625-bodyHeight;
        $(".layui-footer").css("marginTop",tops+"px");
    }
}