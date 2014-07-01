function JSRun(){
	loadMsg();
	
}
function loadMsg(){
	var $code = $("#code"),$info = $("#info");
	var info = $info.html();
	if(info)$info.remove();
	//info = JSON.parse(info);
	var ss = /_ss_/g,se=/_se_/g,bs=/_bs_/g,be=/_be_/g,ls=/_ls_/g,le=/_le_/g,bl=/_bl_/g;
	var pss='<span class="say">',pse='</span><br>',pbl='<span style="visibility:hidden;">&nbsp;&nbsp;</span>',
		pbs= '<span class="black">',pbe='</span>',pls='<a id="link" href="#" onclick="showMap()" >',ple='</a>';
	var msg = [];
	info = info.replace(ss,pss).replace(se,pse).replace(bs,pbs).replace(be,pbe).replace(ls,pls).replace(le,ple).replace(bl,pbl);
	msg.push(info);
	msg.push(pss);
	msg.push('<span class="space"></span>');
	msg.push('-- 曲春屹 王林 敬上');
	msg.push(pse);
	$code.html(msg.join(" "));
	var img = new Image();
	img.src = "./lib/map.jpg";
	var $map=$("<div class='map' style='display:none' />").append(img);
	$("body").append($map);
	var showMap=function(e){
		$map.css({display:'block',top:e.clientX,left:e.clientY});
		$map.fadeIn(2);		
	},
	hiddenMap=function(e){
		$map.fadeOut(2);
	};
	$map.on('click',hiddenMap);
	runCanvas();
	$("#wrap").attr("index",9999);
	var $link = $("#link").parent();
	$link.attr("title","从9号线杨高中路站出发，步行1.4公里，有点小远。也可从上海科技馆坐184路，或世纪公园坐花木1路");
	//$link.on('click',function(e){console.log('click');showMap(e);}).on('mousedown',function(e){showMap(e);console.log("mousedown");});
	//$("#main").on("click",function(e){showMap(e);});
	
}
function hoverHandle(evt){
	
}

function getDays(){
	var together = new Date();
    together.setFullYear(2008, 03, 31); 			//时间年月日
    together.setHours(12);						//小时	
    together.setMinutes(00);					//分钟
    together.setSeconds(0);					//秒前一位
    together.setMilliseconds(2);	
}

function runCanvas(){
	(function(){
        var canvas = $('#canvas');
		
        if (!canvas[0].getContext) {
            $("#error").show();
            return false;
        }

        var width = canvas.width();
        var height = canvas.height();
        
        canvas.attr("width", width);
        canvas.attr("height", height);

        var opts = {
            seed: {
                x: width / 2 - 20,
                color: "rgb(190, 26, 37)",
                scale: 2
            },
            branch: [
                [535, 680, 570, 250, 500, 200, 30, 100, [
                    [540, 500, 455, 417, 340, 400, 13, 100, [
                        [450, 435, 434, 430, 394, 395, 2, 40]
                    ]],
                    [550, 445, 600, 356, 680, 345, 12, 100, [
                        [578, 400, 648, 409, 661, 426, 3, 80]
                    ]],
                    [539, 281, 537, 248, 534, 217, 3, 40],
                    [546, 397, 413, 247, 328, 244, 9, 80, [
                        [427, 286, 383, 253, 371, 205, 2, 40],
                        [498, 345, 435, 315, 395, 330, 4, 60]
                    ]],
                    [546, 357, 608, 252, 678, 221, 6, 100, [
                        [590, 293, 646, 277, 648, 271, 2, 80]
                    ]]
                ]] 
            ],
            bloom: {
                num: 700,
                width: 1080,
                height: 650,
            },
            footer: {
                width: 1200,
                height: 5,
                speed: 10,
            }
        };

        var tree = new Tree(canvas[0], width, height, opts);
        var seed = tree.seed;
        var foot = tree.footer;
        var hold = 1;

        canvas.click(function(e) {
            var offset = canvas.offset(), x, y;
            x = e.pageX - offset.left;
            y = e.pageY - offset.top;
            if (seed.hover(x, y)) {
                hold = 0; 
                canvas.unbind("click");
                canvas.unbind("mousemove");
                canvas.removeClass('hand');
            }
        }).mousemove(function(e){
            var offset = canvas.offset(), x, y;
            x = e.pageX - offset.left;
            y = e.pageY - offset.top;
            canvas.toggleClass('hand', seed.hover(x, y));
        });

        var seedAnimate = eval(Jscex.compile("async", function () {
            seed.draw();
            while (hold) {
                $await(Jscex.Async.sleep(10));
            }
            while (seed.canScale()) {
                seed.scale(0.95);
                $await(Jscex.Async.sleep(10));
            }
            while (seed.canMove()) {
                seed.move(0, 2);
                foot.draw();
                $await(Jscex.Async.sleep(10));
            }
        }));

        var growAnimate = eval(Jscex.compile("async", function () {
            do {
    	        tree.grow();
                $await(Jscex.Async.sleep(10));
            } while (tree.canGrow());
        }));

        var flowAnimate = eval(Jscex.compile("async", function () {
            do {
    	        tree.flower(2);
                $await(Jscex.Async.sleep(10));
            } while (tree.canFlower());
        }));

        var moveAnimate = eval(Jscex.compile("async", function () {
            tree.snapshot("p1", 240, 0, 610, 680);
            while (tree.move("p1", 500, 0)) {
                foot.draw();
                $await(Jscex.Async.sleep(10));
            }
            foot.draw();
            tree.snapshot("p2", 500, 0, 610, 680);

            // 会有闪烁不得意这样做, (＞﹏＜)
            canvas.parent().css("background", "url(" + tree.toDataURL('image/png') + ")");
            canvas.css("background", "#FF5015");
            $await(Jscex.Async.sleep(300));
            canvas.css("background", "none");
        }));

        var jumpAnimate = eval(Jscex.compile("async", function () {
            var ctx = tree.ctx;
            while (true) {
                tree.ctx.clearRect(0, 0, width, height);
                tree.jump();
                foot.draw();
                $await(Jscex.Async.sleep(25));
            }
        }));

        var textAnimate = eval(Jscex.compile("async", function () {
//		    var together = new Date();
//		    together.setFullYear(2008, 03, 31); 			//时间年月日
//		    together.setHours(12);						//小时	
//		    together.setMinutes(00);					//分钟
//		    together.setSeconds(0);					//秒前一位
//		    together.setMilliseconds(2);				//秒第二位

		    $("#code").show().typewriter();
            //$("#clock-box").fadeIn(500);
            //while (true) {
             //   timeElapse(together);
             //   $await(Jscex.Async.sleep(1000));
            //}
        }));
        
        var runAsync = eval(Jscex.compile("async", function () {
            $await(seedAnimate());
            $await(growAnimate());
            $await(flowAnimate());
            $await(moveAnimate());
           
            textAnimate().start();

            $await(jumpAnimate());
        }));

        runAsync().start();
    })();
}
