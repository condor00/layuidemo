/*
* @Author: sun(slf02@ourstu.com)
* @Date:   2018-09-14 10:00:00
*/
layui.define(['laydate', 'jquery', 'moment'], function (exports) {
    "use strict";


    var MOD_NAME = 'timePicker',
        $ = layui.jquery,
        laydate = layui.laydate,
        moment = layui.moment;
    var timePicker = function () {
        this.v = '0.0.1';
    };

    /**
     * 初始化时间选择器
     */
    timePicker.prototype.render = function (opt) {

        var elem = $(opt.elem);

        //默认设置
        var timeStamp = opt.options.timeStamp || false;
        var format = opt.options.format || 'YYYY-MM-DD HH:mm:ss';

        elem.on('click', function (e) {
            e.stopPropagation();

            if ($('.timePicker').length >= 1) {
                $('.timePicker').remove();
                return false;
            }
            var t = elem.offset().top + elem.outerHeight() + "px";
            var l = elem.offset().left + "px";

            var timeDiv = '<div class="timePicker layui-anim layui-anim-upbit" style="left:' + l + ';top:' + t + ';">';
            timeDiv +=
                '<div class="time-div">\n' +
                '    <div class="time-info">\n' +
                '        <ul class="time-day"><span>天</span>\n' +
                '            <li><input type="radio" name="day" value="1">今天</li>\n' +
                '            <li><input type="radio" name="day" value="2">昨天</li>\n' +
                // '            <li><input type="radio" name="day" value="3">明天</li>\n' +
                '        </ul>\n' +
                '        <ul class="time-week"><span>周</span>\n' +
                '            <li><input type="radio" name="day" value="4">本周</li>\n' +
                '            <li><input type="radio" name="day" value="5">上周</li>\n' +
                // '            <li><input type="radio" name="day" value="6">下周</li>\n' +
                '        </ul>\n' +
                '        <ul class="time-month"><span>月</span>\n' +
                '            <li><input type="radio" name="day" value="7">本月</li>\n' +
                '            <li><input type="radio" name="day" value="8">上月</li>\n' +
                // '            <li><input type="radio" name="day" value="9">下月</li>\n' +
                '        </ul>\n' +
                '        <ul class="time-quarter"><span>季度</span>\n' +
                '            <li><input type="radio" name="day" value="10">本季度</li>\n' +
                '            <li><input type="radio" name="day" value="11">上一季度</li>\n' +
                // '            <li><input type="radio" name="day" value="12">下一季度</li>\n' +
                '        </ul>\n' +
                '        <ul class="time-year"><span>年度</span>\n' +
                '            <li><input type="radio" name="day" value="13">本年度</li>\n' +
                '            <li><input type="radio" name="day" value="14">上一年度</li>\n' +
                // '            <li><input type="radio" name="day" value="15">下一年度</li>\n' +
                '        </ul>\n' +
                '    </div>\n' +
                '    <div class="time-custom">\n' +
                '        <div class="layui-timepicker-custom" data-role="display">\n' +
                '            <span>自定义起止时间</span>\n' +
                '            <i class="layui-icon layui-icon-down"></i>\n' +
                '        </div>\n' +
                '        <div class="time-select">\n' +
                '            <input type="text" class="layui-input" id="sTime">\n' +
                // '            <input type="text" class="layui-input" id="eTime">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="time-down">\n' +
                '        <div class="sure" data-role="sure">确定</div>\n' +
                '    </div>\n' +
                '</div>';
            timeDiv = $(timeDiv);
            //渲染
            $('body').append(timeDiv);
            //自定义时间显示
            $('[data-role="display"]').on('click', function () {
                $('.time-select').css('display', 'flex');
                $(this).find('i').remove();
            });
            //自定义时间选择器
            laydate.render({elem: '#sTime',range: '~'});
            // laydate.render({elem: '#eTime'});

            //选择固定日期
            var $li = $('.time-info').children().find('li');
            $li.on('click', function () {
                $('.time-info').children().find('li').removeClass('active');
                if ($(this).children('input').is(':checked')) {
                    $(this).children('input').prop('checked', false);
                } else {
                    $(this).addClass('active');
                    $(this).children('input').prop('checked', true);
                }
            });
            //确定后生成时间区间 如：2018-9-14 - 2018-9-15
            $('[data-role="sure"]').on('click', function () {
                var inputVal = $('.time-info').children().find('input:checked').val();
                var sTime = '';
                var eTime = '';
                switch (inputVal) {
                    case '1'://今天
                        sTime = moment.moment().startOf('day');
                        eTime = moment.moment().endOf('day');
                        break;
                    case '2'://昨天
                        sTime = moment.moment().subtract(1, 'days').startOf('day');
                        eTime = moment.moment().subtract(1, 'days').endOf('day');
                        break;
                    case '3'://明天
                        sTime = moment.moment().subtract(-1, 'days').startOf('day');
                        eTime = moment.moment().subtract(-1, 'days').endOf('day');
                        break;
                    case '4'://本周
                        sTime = moment.moment().startOf('week');
                        eTime = moment.moment().endOf('week');
                        break;
                    case '5'://上周
                        sTime = moment.moment().subtract(1, 'week').startOf('week');
                        eTime = moment.moment().subtract(1, 'week').endOf('week');
                        break;
                    case '6'://下周
                        sTime = moment.moment().subtract(-1, 'week').startOf('week');
                        eTime = moment.moment().subtract(-1, 'week').endOf('week');
                        break;
                    case '7'://本月
                        sTime = moment.moment().startOf('month');
                        eTime = moment.moment().endOf('month');
                        break;
                    case '8'://上月
                        sTime = moment.moment().subtract(1, 'month').startOf('month');
                        eTime = moment.moment().subtract(1, 'month').endOf('month');
                        break;
                    case '9'://下月
                        sTime = moment.moment().subtract(-1, 'month').startOf('month');
                        eTime = moment.moment().subtract(-1, 'month').endOf('month');
                        break;
                    case '10'://本季度
                        sTime = moment.moment().startOf('quarter');
                        eTime = moment.moment().endOf('quarter');
                        break;
                    case '11'://上季度
                        sTime = moment.moment().subtract(1, 'quarter').startOf('quarter');
                        eTime = moment.moment().subtract(1, 'quarter').endOf('quarter');
                        break;
                    case '12'://下季度
                        sTime = moment.moment().subtract(-1, 'quarter').startOf('quarter');
                        eTime = moment.moment().subtract(-1, 'quarter').endOf('quarter');
                        break;
                    case '13'://本年度
                        sTime = moment.moment().startOf('year');
                        eTime = moment.moment().endOf('year');
                        break;
                    case '14'://上年度
                        sTime = moment.moment().subtract(1, 'year').startOf('year');
                        eTime = moment.moment().subtract(1, 'year').endOf('year');
                        break;
                    case '15'://下年度
                        sTime = moment.moment().subtract(-1, 'year').startOf('year');
                        eTime = moment.moment().subtract(-1, 'year').endOf('year');
                        break;
                    default:
                        sTime = $('#sTime').val();
                        eTime = $('#eTime').val();
                        break;
                }
                var timeDate = '';
                if (inputVal) {
                    if (inputVal === '1' || inputVal === '2' || inputVal === '3') {
                        if (timeStamp) {
                            timeDate = sTime;
                        } else {
                            timeDate = sTime.format(format);
                        }
                    } else {
                        if (timeStamp) {
                            timeDate = parseInt(sTime / 1000) + ' ~ ' + parseInt(eTime / 1000);
                        } else {
                            timeDate = sTime.format(format) + ' ~ ' + eTime.format(format);
                        }
                    }

                } else {
                    timeDate = sTime;
                }
                elem.val(timeDate);
                $('.timePicker').remove();
            });
        })
    };

    /**
     * 隐藏选择器
     */
    timePicker.prototype.hide = function (opt) {
        $('.timePicker').remove();
    };

    //自动完成渲染
    var timePicker = new timePicker();

    //FIX 滚动时错位
    $(window).scroll(function () {
        timePicker.hide();
    });

    exports(MOD_NAME, timePicker);
});