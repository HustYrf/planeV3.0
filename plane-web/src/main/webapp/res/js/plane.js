/**
 * jquery form fill
 */
(function ($) {
    function Fill() {
        this.defaults = {
            styleElementName: 'none',	// object | none
            dateFormat: 'mm/dd/yy',
            debug: false,
            elementsExecuteEvents: ['checkbox', 'radio', 'select-one']
        };
    };
    $.extend(Fill.prototype, {
        clear: function (obj, _element) {
            _element.find("*").each(function (i, item) {
                if ($(item).is("input") || $(item).is("select") || $(item).is("textarea")) {
                    try {
                        switch ($(item).attr("type")) {
                            case "hidden":
                            case "password":
                            case "textarea":
                            case "text":
                                $(item).val('');
                                break;
                            case "select":
                                $(item).get(0).selectedIndex = 0;//选中第一个
                                break;
                            case "radio":
                            case "checkbox":
                                $(item).attr("checked", false);
                                break;
                        }
                    } catch (e) {
                    }
                }
            });
        },

        fill: function (obj, _element, settings) {
            this.clear(obj, _element);
            options = $.extend({}, this.defaults, settings);
            _element.find("*").each(function (i, item) {
                if ($(item).is("input") || $(item).is("select") || $(item).is("textarea")) {
                    try {
                        var objName = null;
                        var arrayAtribute = null;
                        var value = null;
                        try {
                            if (options.styleElementName == "object") {
                                if ($(item).attr("name").match(/\[[0-9]*\]/i)) {
                                    objName = $(item).attr("name").replace(/^[a-z]*[0-9]*[a-z]*\./i, 'obj.').replace(/\[[0-9]*\].*/i, "");
                                    arrayAtribute = $(item).attr("name").match(/\[[0-9]*\]\.[a-z0-9]*/i) + "";
                                    arrayAtribute = arrayAtribute.replace(/\[[0-9]*\]\./i, "");
                                } else {
                                    objName = $(item).attr("name").replace(/^[a-z]*[0-9]*[a-z]*\./i, 'obj.');
                                }
                            } else if (options.styleElementName == "none") {
                                objName = 'obj.' + $(item).attr("name");
                            }
                            value = eval(objName);
                        } catch (e) {
                            if (options.debug) {
                                debug(e.message);
                            }
                        }
                        if (value != null) {
                            var _type = $(item).attr("type");
                            if (_type == "hidden" || _type == "password") {
                                $(item).val(value);
                            } else if (_type == "text") {
                                if ($(item).hasClass("hasDatepicker")) {
                                    var re = /^[-+]*[0-9]*$/;
                                    var dateValue = null;
                                    if (re.test(value)) {
                                        dateValue = new Date(parseInt(value));
                                        var strDate = dateValue.getUTCFullYear() + '-' + (dateValue.getUTCMonth() + 1) + '-' + dateValue.getUTCDate();
                                        dateValue = $.datepicker.parseDate('yy-mm-dd', strDate);
                                    } else if (value) {
                                        dateValue = $.datepicker.parseDate(options.dateFormat, value);
                                    }
                                    $(item).datepicker('setDate', dateValue);
                                } else if ($(item).attr("alt") == "double") {
                                    $(item).val(value.toFixed(2));
                                } else {
                                    $(item).val(value);
                                }
                            } else if (_type == "select-one") {
                                if (value) {
                                    $(item).val(value);
                                }
                            } else if (_type == "radio") {
                                $(item).each(function (i, radio) {
                                    if ($(radio).val() == value) {
                                        $(radio).attr("checked", "checked");
                                    }
                                });
                            } else if (_type == "checkbox") {
                                if ($.isArray(value)) {
                                    $.each(value, function (i, arrayItem) {
                                        if (typeof(arrayItem) == 'object') {
                                            arrayItemValue = eval("arrayItem." + arrayAtribute);
                                        } else {
                                            arrayItemValue = arrayItem;
                                        }
                                        if ($(item).val() == arrayItemValue) {
                                            $(item).attr("checked", "checked");
                                        }
                                    });
                                } else {
                                    if ($(item).val() == value) {
                                        $(item).attr("checked", "checked");
                                    }
                                }
                            } else {
                                $(item).val(value);
                            }
                        }
                    } catch (e) {
                        if (options.debug) {
                            debug(e.message);
                        }
                    }
                }
            });
        }
    });
    $.fn.fill = function (obj, settings) {
        $.fill.fill(obj, $(this), settings);
        return this;
    };
    $.fn.clear = function (obj) {
        $.fill.clear(obj, $(this));
        return this;
    };
    $.fill = new Fill();
})(jQuery);

$(document).ready(function () {

    //点击模态框的 x ，关闭模态框
    $('.close').click(function () {
        $('.modal').css('display', 'none');
    });

    $('button.closebtn').click(function () {
        $('.modal').css('display', 'none');
    });

    OcModal = function () {
    };
    OcModal.prototype = {

        //根据id show dialog
        show: function (id) {
            if (id) {
                $('#' + id).find('form').each(function (i, item) {
                    $(item).clear();
                });
                $('#' + id).modal('show');
                $('#_ocAlertTip').hide();
            }
        },

        hide: function (id) {
            $('#' + id).modal('hide');
        },

    };
    Modal = new OcModal();

});


