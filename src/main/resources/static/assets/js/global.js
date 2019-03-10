/**
 * String format {}格式
 */
String.format = function () {
    if (arguments.length === 0)
        return null;
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('{}', '');
        str = str.replace(re, arguments[i]);
    }
    return str;
};