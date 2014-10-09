/*!
jquery.dict.js (v1.0)
http://www.google.com
mailto:anymajoy@gmail.com

Copyright (c) 2013 Any Ma
数据字典下拉框封装
*/

(function($){
	$.fn.dict = function(options) {
        try {
            var defaults = $.fn.dict.defaults;
            var o = $.extend({}, defaults, options);
            for (var prop in o) {
                if (defaults[prop] === undefined) throw ('Invalid option specified: ' + prop + '\nPlease check your spelling and try again.');
            };
            
            if(o.dictCode == null || o.dictCode == ""){
            	throw ('dictCode can not be empty');
            }
            
            var self = this;
            self.combobox({
            	url : o.url,
        		valueField : o.valueField,
        		textField : o.textField,
        		width : o.width,
        		panelHeight : o.panelHeight,
        		method : o.method,
        		editable : o.editable,
        		multiple : o.multiple,
        		readonly : o.readonly,
        		required : o.required,
        		queryParams : {
					"dictCode" : o.dictCode
				},
				onLoadSuccess : function(){
					var data = self.combobox('getData');
					if(data != null){
						var defaultValue = o.current;
						if(defaultValue == null){
							defaultValue = data[0][o.valueField];
						}
						self.combobox('setValue', defaultValue);
					}
					if(null != o.onLoadSuccess){
						o.onLoadSuccess();
					}
				},
				onChange : function(newValue, oldValue){
					if(null != o.onChange){
						o.onChange(newValue, oldValue);
					}
				}
            });
            
        } catch (ex) {
            if (typeof ex === 'object') alert(ex.message); else alert(ex);
        };
    };
    
    $.dictList = function(options) {
        try {
        	var defaults = $.fn.dict.defaults;
            $.ajax({
	            type : defaults.method,
	            url : defaults.url,
	            dataType : "json",
	            data : {
					"dictCode" : options.dictCode
				},
	            success : function (data) {
	            	if(null != options.success){
	            		options.success(data);
					}
	            }
	        });
            
        } catch (ex) {
            if (typeof ex === 'object') alert(ex.message); else alert(ex);
        };
    };
    
	$.fn.dict.defaults = {
		url : global_ctxPath + "/anon/dict/list",
		valueField : "itemCode",
		textField : "itemDesc",
		width : 'auto',
		panelHeight : 'auto',
		method : 'post',
		editable : false,
		multiple : false,
		readonly : false,
		required : false,
		dictCode : null,
		current : null,
		onLoadSuccess : null,
		onChange : null
	};
})(jQuery);