layui.use(['element', 'table'], function () {
    let $ = layui.jquery;
    const table2 = layui.table;

    table2.render({
        elem: '#today',
        url: '/course/today',
        title: '用户数据表',
        done: function () {
            $('th').css({'background-color': '#d6e4f5', 'color': '#fff'});
        },
        cols: [
            [{
                field: 'number',
                title: '节次',

            },
                {
                    field: 's901',
                    title: '901',


                }, {
                field: 's902',
                title: '902',

            }, {
                field: 's903',
                title: '903',

            }, {
                field: 's904',
                title: '904',

            }, {
                field: 's905',
                title: '905',

            }, {
                field: 's906',
                title: '906',

            }
            ]
        ],
    });

    $.getJSON(
        "/platform/getFive",
        res => {
            const platforms = res.data
            let lis = ''

            for (let i = 0; i < platforms.length; i++) {
                const li = '<li style="margin-top: 5px;"><a href="' + platforms[i].url + '" target="_blank" class="layui-btn " style="background:white;color:' + Color() + '">' + platforms[i].name + '</a></li>'
                lis += li
            }

            $("#platformUl").html(lis);
        }
    )

    $.getJSON(
        "/notice/getFive",
        res => {
            const notices = res.data
            let lis = ''

            for (let i = 0; i < notices.length; i++) {
                const li = '<li><a target="_blank" href="/noticeInfo?id=' + notices[i].id + '">' + notices[i].title + '<span style="float: right;">' + notices[i].date + '</span></a></li>'
                lis += li
            }

            $("#noticeUL").html(lis);
        }
    )

    function Color() {
        const r = Math.floor(Math.random() * 180);
        const g = Math.floor(Math.random() * 180);
        const b = Math.floor(Math.random() * 180);
        return 'rgba(' + r + ',' + g + ',' + b + ',0.8)';
    }

});

//轮播图
var certifySwiper = new Swiper('#certify .swiper-container', {
    watchSlidesProgress: true,
    autoplayDisableOninteraction: false,
    slidesPerView: 'auto',
    centeredSlides: true,
    loop: true,
    loopedSlides: 5,
    autoplay: true,
    pagination: {
        el: '.swiper-pagination',
        //clickable :true,
    },
    on: {
        setTransition: function (transition) {
            for (var i = 0; i < this.slides.length; i++) {
                var slide = this.slides.eq(i)
                slide.transition(transition);
            }

        }
    }

})