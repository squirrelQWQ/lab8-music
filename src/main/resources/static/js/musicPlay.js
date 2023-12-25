

// 获取 audio 元素
// var audioElement = document.getElementById('play1');
//
// // 使用 JavaScript 设置新的 src
// var newSrc = 'data:audio/mpeg;base64,' + ;
// audioElement.src = newSrc;

var play1 = document.querySelector('#play1');
var play2 = document.querySelector('#play2');
var play3 = document.querySelector('#play3');
var play4 = document.querySelector('#play4');
var play5 = document.querySelector('#play5');
var count = 0;
var button = document.querySelector('.circle').querySelectorAll('li');
var allmusic = [play1, play2, play3, play4, play5];
var line = document.querySelector('.line');
var load = document.querySelector('.load');
var first = document.querySelector('.first');
var seconed = document.querySelector('.seconed');
var img = document.querySelector('img');
var music = document.querySelector('.music');
var click2 = document.querySelector('.click2');
var word = document.querySelector('.word');
var think2s = document.querySelector('#yuanli2');
var think2 = document.querySelector('.think2');
var index = 0;
var length;

button[1].addEventListener('click', function() {
    if (count == 0) {
        allmusic[index].play();
        count++;
        clearInterval(timer);
        jindu();
        numdisplay();
        pic();
        rotate.star();
        /* background: */
        click2.style.background = " url(images/index_z_9769688.png) -811px -643px no-repeat";

    } else {
        allmusic[index].pause();
        rotate.end();
        count--;
        click2.style.background = " url(images/index_z_9769688.png) -1001px -671px no-repeat";

    }
});
// 切换歌曲
var cut = {
    pre: function() {
        index--;
        if (index < 0) {

            return index = allmusic.length - 1;
        } else {
            return index;
        }
    },
    next: function() {
        index++;
        if (index > allmusic.length - 1) {
            return index = 0;
        } else {
            return index;
        }
    }
};
button[0].addEventListener('click', function() {
    count = 1;
    var i = cut.pre();
    if (i == 1 || i == 2) {
        word.style.display = 'block';
    } else {
        word.style.display = 'none';
    }
    clearInterval(timer);
    jindu();
    for (let i = 0; i < allmusic.length; i++) {
        allmusic[i].load();
    }
    allmusic[i].play();
    pic();
    rotate.end();
    rotate.star();
    numdisplay();
    click2.style.background = " url(images/index_z_9769688.png) -811px -643px no-repeat";

});


button[2].addEventListener('click', fn);

function fn() {
    count = 1;
    var i = cut.next();
    if (i == 1 || i == 2) {
        word.style.display = 'block';
    } else {
        word.style.display = 'none';
    }
    clearInterval(timer);
    jindu();
    for (let i = 0; i < allmusic.length; i++) {
        allmusic[i].load();
    }
    allmusic[i].play();
    pic();
    rotate.end();
    rotate.star();
    numdisplay();
    click2.style.background = " url(images/index_z_9769688.png) -811px -643px no-repeat";

};
// 进度条
var timer = null;

function jindu() {
    var cur = allmusic[index].duration;
    // console.log(cur);
    // console.log(index);
    // console.log(load.offsetWidth - 2);
    timer = setInterval(function() {
        length = allmusic[index].currentTime;
        line.style.width = parseFloat(length / cur) * 150 + 'px';
    }, 50);
};
// 数字进度显示和自动播放下一首
var timers = null;

function numdisplay() {
    timers = setInterval(function() {
        var sec = parseInt(length % 60);
        var min = parseInt(length / 60);
        sec = sec >= 10 ? sec : '0' + sec;
        first.innerHTML = sec;
        seconed.innerHTML = min;
        if (allmusic[index].currentTime == allmusic[index].duration) {
            button[2].click();
            clearInterval(timers);
        }

    }, 1000);
};
// 图片切换
function pic() {
    img.src = 'images/0' + index + '.jpg'
    // console.log(index);
};
// 图片点击
img.addEventListener('click', function() {
    button[1].click();
})
// 图片旋转
var timerss = null;
var step = 0;
var rotate = {
    star: function() {
        timerss = setInterval(function() {
            step = step + 2;
            if (step >= 360) {
                step = 0;
            }
            img.style.transform = 'rotate(' + step + 'deg)';
            if (allmusic[index].currentTime == allmusic[index].duration) {
                clearInterval(timerss);
            }
        }, 100)
    },
    end: function() {
        clearInterval(timerss);
    }
};
// 点击进度条实现歌曲拖拽
load.addEventListener('mousedown', function(e) {
    var x = (e.pageX - (this.offsetLeft + music.offsetLeft - 76));
    console.log(x);
    allmusic[index].currentTime = (x * allmusic[index].duration) / 150;

});
// 调节音量
allmusic[index].volume = 0.5;
var bar = document.querySelector('.bar');
var volte = document.querySelector('.volte');
var firstss;
bar.style.top = '20px'
bar.addEventListener('mousedown', function(e) {
    firstss = e.pageY - (this.offsetTop + volte.offsetTop + music.offsetTop);
    // console.log(first);
    bar.addEventListener('mousemove', fn1);

    function fn1(e) {
        var y = e.pageY - (volte.offsetTop + music.offsetTop);
        // console.log(y);
        bar.style.top = y - firstss + 'px';
        if (bar.offsetTop <= 0) {
            bar.style.top = 0;
        }
        if (bar.offsetTop >= 45) {
            bar.style.top = 45 + 'px';
        }
        allmusic[index].volume = (bar.offsetTop / 45);

        // console.log(allmusic[index].volume);
    }
    document.addEventListener('mouseup', function() {
        bar.removeEventListener('mousemove', fn1);

    })
})

// 原理显示
var yuanli = document.querySelector('#yuanli1');
var think = document.querySelector('.think');
var closebox = document.querySelector('.close-box');
var jiantou = document.querySelector('.jiantou');
think.addEventListener('click', function() {
    yuanli.style.display = 'block';
    closebox.style.display = 'block';
    jiantou.style.display = 'block';
});
var jiantoui = 1;
jiantou.addEventListener('click', function() {
    if (jiantoui == 1) {
        jiantoui++;
        jiantou.style.transform = 'rotate(-135deg)';
        jiantou.style.left = '-41px';
        yuanli.src = 'images/' + jiantoui + '.png'
    } else {
        jiantoui--;
        jiantou.style.transform = 'rotate(45deg)';
        jiantou.style.left = '';
        jiantou.style.right = '-41px';
        yuanli.src = 'images/' + jiantoui + '.png'
    }
})
closebox.addEventListener('click', function() {
    yuanli.style.display = 'none';
    closebox.style.display = 'none';
    jiantou.style.display = 'none';
    think2s.style.display = 'none';
})
think2.addEventListener('click', function() {
    think2s.style.display = 'block';
    closebox.style.display = 'block';
})