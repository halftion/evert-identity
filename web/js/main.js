/* Tab切换 */
$(function(){
    $('#tabs li').click(function(e) {
         e.preventDefault();                
         $('#tabs li').removeClass("select");
         $(this).addClass("select");
         $("#content ul").removeClass("show");
         $('#' + $(this).attr('title')).addClass('show');
     });
 });

/* 表单提交 */
function opear(obj){
    var content = $(obj).val();
    if(content.length>0){
        $("#subForm").submit();
    }
   }

