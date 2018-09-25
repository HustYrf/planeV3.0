$(document).ready(function(){

    //点击模态框的 x ，关闭模态框
    $('.close').click(function(){
        $('.modal').css('display','none');
    });

    OcModal = function (){};
    OcModal.prototype = {

        //根据id show dialog
        show : function(id){
            if(id){
                $('#' + id).find('form').each(function(i,item){
                    $(item).clear();
                });
                $('#' + id).modal('show');
                $('#_ocAlertTip').hide();
            }
        },

        hide : function(id){
            $('#' + id).modal('hide');
        },

    };
    Modal = new OcModal();

});
