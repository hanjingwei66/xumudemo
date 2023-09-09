<template>
  <div class="sailing">
    <div class="container">
      <div class="left">
        <div class="header">
          <div class="head-top">
            <img class="headImg" alt=""
                 src="https://sphere-sh.oss-cn-shanghai.aliyuncs.com/upload/file/2023-06-21/f563b69218dc43a90f1c7b2169976e06.png_bg"
                 onerror="this.onerror=null;this.src='/images/pic_empty_head.jpg';">
          </div>
          <div class="info">
            <div class="title">
              <span>{{ savedData.articleTitleName }}</span>
            </div>
            <div class="hint">
              <span>{{ savedData.articleDescribe }}</span>
            </div>
            <div class="expire">
              <span>截止报名：2023/06/16 23:59  星期五</span>
            </div>
          </div>
        </div>

        <div class="content">
          <div  class="top">活动详情</div>
          <div  class="img-box">
            <div class="activeIntroduce vc-pc" v-for="items in template">
              <div class="cardActiveList">
                <div class="cardHeadImg"><img
                  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABU4AAABQCAMAAADx/x+GAAAC/VBMVEUAAAA1ppA2pZAwn483pZA0o48wn482ppA2pZA2pZD/////68+fyrE4ppH/687/6sw+qJM5ppFEqpQ9p5I3pZD/79pYsZqQwaU7p5FWr5hOrZbg4sn/58NxuJ9Zsp1Qr5pOrplLrZdHrJdIq5VBqZTe4cfg38OGwapxu6VuuaJktZ9UsJpDqpb/7NHn5cv/5b/I1rlZtaNetJ5Gq5X76c3/58aCwat9vqdot6BfsplSr5dBqZP/8+L/8t/q6dSn2M7/6Mfr48Xm4sXV3sXD2cOo0sDf3b2n0Luqz7eVxap6v6psuqV1uZ9otZxcsZrx6dH769DK28Su1cPo4cLk38HP28HT3MDo37+61b6l0b7/47zq3rvZ27vP2Lu607n/4renzreTybaUx6+Lw6yax6t1vqqPw6hpuaV/vaOCvaFbtJ9st57y+fj/8dzr69ji5tL36c/k5c7R4c7g5M3358vT38m01cCCyLr34Ljn3Lje2ri80rS20LTZ17KXybKKxrHI0q99wa1nu6ujx6iLwqebxKV2vKVRsZ5htJ1Mrpvo69v+79ff6Nfa5tXe5dL+6s3z58rj48rZ4cr05sjb38W418L048HW277r3rnw3riv0LfD1LWfzLWcy7Xk2rPQ1rOExLGpy7Cgxqhlt6N7u6FetaFks5pLq5Tn9PLg8u//9unK5+LE5d7m6tm54Nnv69jk6Nb37NWx3dTO4tLm59HT4tHs5s3u5cqZ0cf858bH28bR3cWPzcGLy7+jzrjV17SPx7TM1LOuzrKmzLGPxa+Gw65svq60za2lya2eyK2wy6pxvKlfuKeHwKRktJz/9ef/9OT38+Tz7+Do5s7G3s3M38zE3MrH2cHr4L/A1r304byazbuVyrjA1LeZybGdybCiyq6ry6yOw6upyKeCwKeSwqaLv6NVsZzs9/Xt9vXa7+vX7unN6ePo7eD4793C5N2/49yu29Oj1cuc08iY0cau0ryq0Lp5w7W90LC6zq/Bz61Ws6C92ci/2seh0cJXdqBpAAAACXRSTlMAj88Qn0Ag398gP1mnAAAJfklEQVR42uzbP2gaUQDH8dP8aX8PhEo6dErska0uaVfF6Qjo5uboIIKIBYc6ii4SUbfuggiJialEhAwJZMpiwD8YF93Tf0n/Qof+oacW2nJSvbrl/T7T3b3p3vDl8XhPGVmx3AEREf0X6/KqMrFkARERLcCyNK6pFUREtBDrqKdcmxIRLcyiKKswJ6JCalsgIppiRVmGOcUypPbpHoiIjCyKFeb8KEJqB1yeEtE0dxWY1PNDansJEBFNocCc+9d1yGxHeEBEZGA+p47KtdSbh+fiAEREBuZzWqp01iGxE/ESREQG5nN6VqmEILFDIXZARGSkwBx/pzWEtGyZPSE+fwURkYECEzbLoVy9VW+uS7h96rxIePajYiz61pNISzgHRPQvCuakNrVcNt/o+luduv6g+ZyQydbxu9fiD7uHxzyASkS/zZtTW8RfbQzCqi2SC7S7TvuGb5DPaVeQizN9ehQV4s2H07TkN22JyGDOnJb72UHGBl2qF2i3y8CksP4MZHMkhHw/TUTzUDDTmpYNqpgYBjSXq4gJR6AafAC5nIhXICIymCen5fw3B0YeFv2NG6+e025D825i5Coe34BUzsU+iIgMZufUnnqRsgFwhnqxfrBUiwRcrnh4eFn97lsbDZ9l5TqGykumRDTV7JwGY48B2Jv5ePEJoNZUPae1Tdg2koXLMHTNWgoy2f0CIiKDmTkNPl0H4OgXQmvQleL2gNt98wg6e7KgqaNvMS8k8v4CREQGs3KaHNfUV9h2/np/hm232+3DmON5fDwcC+MW28JfPtpB9JO9u2tNKg7gOO7tj7Qdjw+sJB+az6YMJyoFwlTIFuSkFG8S06IIasp0V7HoRgZBeyYY21UvIAZjgwYjansFEWMXbTAGvYK6zuPcYrgOJ7o7/T6XHr39cuD8z0+iXvI5DSf8AKKJR0Ycm3jQyelHHDO+fC+V9NH7IajXmfl9/rkJEZ1PPqfiWBTAyO+bT/3Xx7j57NmzRZzYeX4bwIMPOqgW5/eJSAH5nG5JlfS/H1m04di1MUcnp2/Q5V0cSTwFhOZLqBbn94lIAdmchhN2wD7mxavEKyMk1rF+3DSZTKsCJMLNRAAfmyLwbk21b13yZBQRKSGb04llAIs/AeOE6YcTba0P6OTU9BRt1qYpChjSK7r2N1agUpzfJyIl5HL6ffwq4J0TAYhra2t+AMvLQNnUFgX0O9nVCR0AR8IKBBJqvT3l/D4RKSGX0x9bgNAcgSQ+vlNfcSId6+Z0C4F0c3t8AJLJNGBM70CdOL9PRErI5PTauBVozenQsfzBlsvZxm+P/Ky/bkt761u2bBwdQ9I3vXNQI87vE5EyMjn1beqAnAfHDM2KbrK+mt1c8Xon09nVOZ9hdwVd5TIg1ANQF87vE9FfkMlpZhIQv9jRFf5idbrq3n5Et4Vw8cueoZoS0BVY1wPFKlSF8/tE9DdkcpryAdE0TkXncpsDAGaz9srr7WLOYsMJoyUMRItQHc7vE5FyGpzPELIDS9to04kBX6VoCYYGDE6nMJs1B2L2a7nsQrHiszl1wPGdrHkWqsT5fSJSRoPzmS16IBUfCnsyu5ZQvuzxrXugtVgso6OzFssk/C6vJ7MZcu1molaxugc4a06oEef3iUgZDc7nywdalVoolCrt+2yCEYi5RCy7XK5aMBQMumDM+QGjYPNqiwuhUKjqDayrc5ef8/tEpIwGvW6EPUsLNVe+HJtyGNC1VNKhXxAES2096IobsV9Fl8Hh92Tys6OpcuyOCLXhS6ZEpERPTgOx/ZJlNBgcTWW0Zy0cQDK1EaxpBQCxlPasTEr6natUaTmgJpzfJyIFenLqiFeXChuRC+fQSpcPIhcafkiOLvSabiTzSx7/DagJ5/eJSBENeg2FYw8LkcbFs17oEV+4uLExY3aKBuDz2avTM5G9SsuswqPunN8nIgXkHkUNxQ9nCsORvhNJB0p9fY1I3/zMTB79ydMLkeG3Sc/UwLAdqsTD+0SkgMxBKbceKMRFc+tgMNlp6rQH/oLbPT/tdrtfGI+mpc8aydItn1mslgCxob7HUERE/57T/uHLwOeH6LjhP/xUiCT9aHPPXwZwPTl//7NnSkDHp0PgjhtERP8xDf7g7RXgySBOxdyD7jiAwYIDMLu/3buEE/q7YeDoBYiI/mO/2rl71rTCMA7jrv/hQNSgHqlDcepUoXTQLM0iCJrFgBAVlxAUv4AvFQQjdC8YRVAU0c+QKaQd8gLJHAKFhJB8hrZTj5p2qjbFDqHn+o2HM188Dw/3vTCnN2eSK/lSjyLJVxuh7VjQ8PmcwdjHT2ufQ4YeeZJOqbovALCxhTktHvikL3uaM7Jnzvzm4eHml8Egu5k8PLj0ZX/1c3Ajubb9AgAbW5jTN9seqZhxa2Y/669GP2y/s2RjiVFoP7m3tWlq5mUyIsUP1gUANubQIjenkjub19SH1kX0vUvVd5Y9afdMZiYUi85bG6tK613u+gDsbXFOI6mIlJjd4YOZTHQkKfbWsid185J7N3o1mP/nkTzbbwQAdrY4p+vVM8lZ/SRp8DbklyUwz6kz45GlmGoFJHd3V1p/vysAsDWHFvKkTCkYHSne2vdpyjx88eLFnvwpt6Ym76N+7YZ8UjH6f83pA8C/zKlOu4Z0eRXvXmrOlZrlNJHRnHFxmk9FJGclLwCwt2U5dWa8kgKtgB4Zx1ZOvSp800/eVljS6VfWhACwO4eW2LoKSAqkC4bmbmc5vb3XnPM+vSUpn+YdCoDtObRMPB2WFK6cuDRz8fr1a6/6Rc2Y349MSUWrqQBgdw4tdVEJS3pzUi6syTKe5nQtHZFlI5YeTj8GygEBgO05tJy3nJAl0a88+KXgtZVTf2VD8g8r9bAhqVCOCwDg0B8Erh8MSUbi6/VxM962cho/GjWOOudhWXzD9lgAADn0J57+sakpV6Bx3NnZ8Q7b9UZiTVPjfj0oAMBTcipXs30X1FzTyum3gubM27LXEADgaTmVzJNyY+yTZbSzc1/bksWdOGk3JgIAPD2nUuSuV2sUJ4ZZ2mn2NoxJ/LzW8/o5mgLAX+ZUcl4O+6VOrZQ7L9U6nXozTEsBYHFOl1vzh49ytfrWhJlSAFglp5bzXMkrAMCqOX3I5RiCAoDVczrOlUwBAFbNqSvXY1M0AKye0/VeXQCAlXOq73cCAKye02ZBAIDVc1oICwCwek5NVkgBwG9zCgAgpwDwXJBTACCnAPB8kFMAIKcA8Hz8AANX/yiip6eHAAAAAElFTkSuQmCC"
                  alt="" class="starBg">
                  <div class="titleMains">
                    <div class="subject"><span>{{ items.title }}</span></div>
                  </div>
                </div>
                <div class="cardContent" >
                  <div v-for="item in items.content">
                    <templet-title  :text="item.text" v-if="item.type === undefined && item.order!=true"></templet-title>
                    <templet-children-ul  :text="item.text" v-if="item.type"></templet-children-ul>
                    <templet-content-stage  :text="item.text" v-if="item.type == 'head'"></templet-content-stage>
                  </div>
                </div>
              </div>
            </div>
            <div  class="activeIntroduce vc-pc">
              <div class="cardActiveList">
                <div class="cardHeadImg">
                  <div class="imgBottom"><img
                    src="data:image/png;base64,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"
                    alt="" class="starBg">
                    <div class="titleMains">
                      <div class="subject"><span>航海公约</span></div>
                    </div>
                  </div>
                </div>
                <div class="cardContent">
                  <div class="contentWord"><span
                    class="titleTop">参与生财有术航海实战，你将会：</span>
                    <div class="name"><span
                      class="titles">深入了解一线、可落地项目的信息与机会</span>
                      <div class="ul">
                        <div class="li">
                          <div class="circle"></div>
                          <span class="lis">精选一线前沿实战项目机会</span></div>
                        <span
                          class="liChild">每一次航海实战，生财有术团队会对几十个项目深入了解、沟通和调研，最后精选并上线满足落地性和前沿性的实战项目</span>
                      </div>
                      <div class="ul">
                        <div class="li">
                          <div class="circle"></div>
                          <span class="lis">邀请多位实战派高手分享</span></div>
                        <span
                          class="liChild">航海群内将定期邀请组织多位大咖嘉宾、实战教练、优秀船员等高手分享最一线的实操认知与经验</span>
                      </div>
                      <div class="ul">
                        <div class="li">
                          <div class="circle"></div>
                          <span class="lis">大小群信息同步机制</span></div>
                        <span
                          class="liChild">各航海项目会分别建立大群、小群。大群用于全员日常交流、答疑及分享；小群用于同步重要信息，不漏掉每一条重要消息</span>
                      </div>
                    </div>
                    <div class="name"><span
                      class="titles">通过航海体系陪伴倒逼行动发生</span>
                      <div class="ul">
                        <div class="li">
                          <div class="circle"></div>
                          <span class="lis">志愿者航海陪伴监督机制</span></div>
                        <span class="liChild">航海之旅并不孤独，约 30 位的船员由 1 位航海志愿者（同为生财圈友）陪伴成长与监督行动，共享目标、 共同突破</span>
                      </div>
                      <div class="ul">
                        <div class="li">
                          <div class="circle"></div>
                          <span class="lis">保证金退还机制</span></div>
                        <span class="liChild">参与航海须缴纳保证金。21 天航海周期内，完成不少于 12 次航海日志，保证金将全额返还；若记录日志数量不足 12 次，则不退保证金。（航海开船后的前三天，可申请下船并退还保证金）</span>
                      </div>
                    </div>
                  </div>
                  <div class="contentWord"><span
                    class="titleTop">参与生财有术航海实战，你需要：</span>
                    <div class="ul">
                      <div class="li">
                        <div class="circle"></div>
                        <span class="lis">完成不少于 12 次航海日志记录</span></div>
                      <span class="liChild">在 21 天航海周期内，每位船员需记录不少于 12 篇航海日志。日志内容以项目相关实操为主，按照「记录-复盘-计划」的形式记录，并将展示给所有船员</span>
                    </div>
                    <div class="ul">
                      <div class="li">
                        <div class="circle"></div>
                        <span class="lis">更积极主动的航海姿态</span></div>
                      <span
                        class="liChild">在实战生财的路上，生财有术是配角，而你是主角，这需要你更积极主动地学习、行动、探索、查找和求助，为自己的成长和赚钱负全部责任</span>
                    </div>
                    <div class="ul">
                      <div class="li">
                        <div class="circle"></div>
                        <span class="lis">尊重每一次行动的发生</span></div>
                      <span
                        class="liChild">你的每一步行动，都是在为未来做铺垫。请正视每一篇航海日志，不做无意义的日志记录。无意义的日志将不计入日志总数</span>
                    </div>
                    <div class="ul">
                      <div class="li">
                        <div class="circle"></div>
                        <span class="lis">尊重共同的航海公约</span></div>
                      <span
                        class="liChild">无规矩不成方圆，航海公约的维护离不开每一个人的主动遵守。恶意违反航海公约，将会被工作人员在航海群内公示并移出航海群，且后续将不再允许参加航海实战活动</span>
                    </div>
                    <div class="ul">
                      <div class="li">
                        <div class="circle"></div>
                        <span class="lis">尊重每一位同行者</span></div>
                      <span
                        class="liChild">践行真诚、利他、开放、空杯的生财有术价值观，尊重每一位同行的教练、志愿者、船员、生财团队，不尊重他人者将会被列入「黑名单」无法参与后续活动</span>
                    </div>
                    <div class="ul">
                      <div class="li">
                        <div class="circle"></div>
                        <span class="lis">尽你所能帮助他人</span></div>
                      <span
                        class="liChild">每个人都能帮助到其他人，只要你想，期待这 21 天的航海，成为你的成就之旅、难忘之旅、利他之旅</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="cardActiveList">
                <div class="cardHeadImg">
                  <div class="imgBottom"><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABU4AAABQCAMAAADx/x+GAAAC/VBMVEUAAAA1ppA2pZAwn483pZA0o48wn482ppA2pZA2pZD/////68+fyrE4ppH/687/6sw+qJM5ppFEqpQ9p5I3pZD/79pYsZqQwaU7p5FWr5hOrZbg4sn/58NxuJ9Zsp1Qr5pOrplLrZdHrJdIq5VBqZTe4cfg38OGwapxu6VuuaJktZ9UsJpDqpb/7NHn5cv/5b/I1rlZtaNetJ5Gq5X76c3/58aCwat9vqdot6BfsplSr5dBqZP/8+L/8t/q6dSn2M7/6Mfr48Xm4sXV3sXD2cOo0sDf3b2n0Luqz7eVxap6v6psuqV1uZ9otZxcsZrx6dH769DK28Su1cPo4cLk38HP28HT3MDo37+61b6l0b7/47zq3rvZ27vP2Lu607n/4renzreTybaUx6+Lw6yax6t1vqqPw6hpuaV/vaOCvaFbtJ9st57y+fj/8dzr69ji5tL36c/k5c7R4c7g5M3358vT38m01cCCyLr34Ljn3Lje2ri80rS20LTZ17KXybKKxrHI0q99wa1nu6ujx6iLwqebxKV2vKVRsZ5htJ1Mrpvo69v+79ff6Nfa5tXe5dL+6s3z58rj48rZ4cr05sjb38W418L048HW277r3rnw3riv0LfD1LWfzLWcy7Xk2rPQ1rOExLGpy7Cgxqhlt6N7u6FetaFks5pLq5Tn9PLg8u//9unK5+LE5d7m6tm54Nnv69jk6Nb37NWx3dTO4tLm59HT4tHs5s3u5cqZ0cf858bH28bR3cWPzcGLy7+jzrjV17SPx7TM1LOuzrKmzLGPxa+Gw65svq60za2lya2eyK2wy6pxvKlfuKeHwKRktJz/9ef/9OT38+Tz7+Do5s7G3s3M38zE3MrH2cHr4L/A1r304byazbuVyrjA1LeZybGdybCiyq6ry6yOw6upyKeCwKeSwqaLv6NVsZzs9/Xt9vXa7+vX7unN6ePo7eD4793C5N2/49yu29Oj1cuc08iY0cau0ryq0Lp5w7W90LC6zq/Bz61Ws6C92ci/2seh0cJXdqBpAAAACXRSTlMAj88Qn0Ag398gP1mnAAAJfklEQVR42uzbP2gaUQDH8dP8aX8PhEo6dErska0uaVfF6Qjo5uboIIKIBYc6ii4SUbfuggiJialEhAwJZMpiwD8YF93Tf0n/Qof+oacW2nJSvbrl/T7T3b3p3vDl8XhPGVmx3AEREf0X6/KqMrFkARERLcCyNK6pFUREtBDrqKdcmxIRLcyiKKswJ6JCalsgIppiRVmGOcUypPbpHoiIjCyKFeb8KEJqB1yeEtE0dxWY1PNDansJEBFNocCc+9d1yGxHeEBEZGA+p47KtdSbh+fiAEREBuZzWqp01iGxE/ESREQG5nN6VqmEILFDIXZARGSkwBx/pzWEtGyZPSE+fwURkYECEzbLoVy9VW+uS7h96rxIePajYiz61pNISzgHRPQvCuakNrVcNt/o+luduv6g+ZyQydbxu9fiD7uHxzyASkS/zZtTW8RfbQzCqi2SC7S7TvuGb5DPaVeQizN9ehQV4s2H07TkN22JyGDOnJb72UHGBl2qF2i3y8CksP4MZHMkhHw/TUTzUDDTmpYNqpgYBjSXq4gJR6AafAC5nIhXICIymCen5fw3B0YeFv2NG6+e025D825i5Coe34BUzsU+iIgMZufUnnqRsgFwhnqxfrBUiwRcrnh4eFn97lsbDZ9l5TqGykumRDTV7JwGY48B2Jv5ePEJoNZUPae1Tdg2koXLMHTNWgoy2f0CIiKDmTkNPl0H4OgXQmvQleL2gNt98wg6e7KgqaNvMS8k8v4CREQGs3KaHNfUV9h2/np/hm232+3DmON5fDwcC+MW28JfPtpB9JO9u2tNKg7gOO7tj7Qdjw+sJB+az6YMJyoFwlTIFuSkFG8S06IIasp0V7HoRgZBeyYY21UvIAZjgwYjansFEWMXbTAGvYK6zuPcYrgOJ7o7/T6XHr39cuD8z0+iXvI5DSf8AKKJR0Ycm3jQyelHHDO+fC+V9NH7IajXmfl9/rkJEZ1PPqfiWBTAyO+bT/3Xx7j57NmzRZzYeX4bwIMPOqgW5/eJSAH5nG5JlfS/H1m04di1MUcnp2/Q5V0cSTwFhOZLqBbn94lIAdmchhN2wD7mxavEKyMk1rF+3DSZTKsCJMLNRAAfmyLwbk21b13yZBQRKSGb04llAIs/AeOE6YcTba0P6OTU9BRt1qYpChjSK7r2N1agUpzfJyIl5HL6ffwq4J0TAYhra2t+AMvLQNnUFgX0O9nVCR0AR8IKBBJqvT3l/D4RKSGX0x9bgNAcgSQ+vlNfcSId6+Z0C4F0c3t8AJLJNGBM70CdOL9PRErI5PTauBVozenQsfzBlsvZxm+P/Ky/bkt761u2bBwdQ9I3vXNQI87vE5EyMjn1beqAnAfHDM2KbrK+mt1c8Xon09nVOZ9hdwVd5TIg1ANQF87vE9FfkMlpZhIQv9jRFf5idbrq3n5Et4Vw8cueoZoS0BVY1wPFKlSF8/tE9DdkcpryAdE0TkXncpsDAGaz9srr7WLOYsMJoyUMRItQHc7vE5FyGpzPELIDS9to04kBX6VoCYYGDE6nMJs1B2L2a7nsQrHiszl1wPGdrHkWqsT5fSJSRoPzmS16IBUfCnsyu5ZQvuzxrXugtVgso6OzFssk/C6vJ7MZcu1molaxugc4a06oEef3iUgZDc7nywdalVoolCrt+2yCEYi5RCy7XK5aMBQMumDM+QGjYPNqiwuhUKjqDayrc5ef8/tEpIwGvW6EPUsLNVe+HJtyGNC1VNKhXxAES2096IobsV9Fl8Hh92Tys6OpcuyOCLXhS6ZEpERPTgOx/ZJlNBgcTWW0Zy0cQDK1EaxpBQCxlPasTEr6natUaTmgJpzfJyIFenLqiFeXChuRC+fQSpcPIhcafkiOLvSabiTzSx7/DagJ5/eJSBENeg2FYw8LkcbFs17oEV+4uLExY3aKBuDz2avTM5G9SsuswqPunN8nIgXkHkUNxQ9nCsORvhNJB0p9fY1I3/zMTB79ydMLkeG3Sc/UwLAdqsTD+0SkgMxBKbceKMRFc+tgMNlp6rQH/oLbPT/tdrtfGI+mpc8aydItn1mslgCxob7HUERE/57T/uHLwOeH6LjhP/xUiCT9aHPPXwZwPTl//7NnSkDHp0PgjhtERP8xDf7g7RXgySBOxdyD7jiAwYIDMLu/3buEE/q7YeDoBYiI/mO/2rl71rTCMA7jrv/hQNSgHqlDcepUoXTQLM0iCJrFgBAVlxAUv4AvFQQjdC8YRVAU0c+QKaQd8gLJHAKFhJB8hrZTj5p2qjbFDqHn+o2HM188Dw/3vTCnN2eSK/lSjyLJVxuh7VjQ8PmcwdjHT2ufQ4YeeZJOqbovALCxhTktHvikL3uaM7Jnzvzm4eHml8Egu5k8PLj0ZX/1c3Ajubb9AgAbW5jTN9seqZhxa2Y/669GP2y/s2RjiVFoP7m3tWlq5mUyIsUP1gUANubQIjenkjub19SH1kX0vUvVd5Y9afdMZiYUi85bG6tK613u+gDsbXFOI6mIlJjd4YOZTHQkKfbWsid185J7N3o1mP/nkTzbbwQAdrY4p+vVM8lZ/SRp8DbklyUwz6kz45GlmGoFJHd3V1p/vysAsDWHFvKkTCkYHSne2vdpyjx88eLFnvwpt6Ym76N+7YZ8UjH6f83pA8C/zKlOu4Z0eRXvXmrOlZrlNJHRnHFxmk9FJGclLwCwt2U5dWa8kgKtgB4Zx1ZOvSp800/eVljS6VfWhACwO4eW2LoKSAqkC4bmbmc5vb3XnPM+vSUpn+YdCoDtObRMPB2WFK6cuDRz8fr1a6/6Rc2Y349MSUWrqQBgdw4tdVEJS3pzUi6syTKe5nQtHZFlI5YeTj8GygEBgO05tJy3nJAl0a88+KXgtZVTf2VD8g8r9bAhqVCOCwDg0B8Erh8MSUbi6/VxM962cho/GjWOOudhWXzD9lgAADn0J57+sakpV6Bx3NnZ8Q7b9UZiTVPjfj0oAMBTcipXs30X1FzTyum3gubM27LXEADgaTmVzJNyY+yTZbSzc1/bksWdOGk3JgIAPD2nUuSuV2sUJ4ZZ2mn2NoxJ/LzW8/o5mgLAX+ZUcl4O+6VOrZQ7L9U6nXozTEsBYHFOl1vzh49ytfrWhJlSAFglp5bzXMkrAMCqOX3I5RiCAoDVczrOlUwBAFbNqSvXY1M0AKye0/VeXQCAlXOq73cCAKye02ZBAIDVc1oICwCwek5NVkgBwG9zCgAgpwDwXJBTACCnAPB8kFMAIKcA8Hz8AANX/yiip6eHAAAAAElFTkSuQmCC"
                    alt="" class="starBg">
                    <div class="titleMains">
                      <div class="subject"><span>注意事项</span></div>
                    </div>
                  </div>
                </div>
                <div class="cardContent">
                  <div class="contentWord">
                    <div class="ulThree">
                      <div class="liThree">
                        <div class="circle"></div>
                        <span
                          class="lis">生财有术航海实战是所有会员的专属权益活动，可以在活动期间参与报名</span></div>
                    </div>
                    <div class="ulThree">
                      <div class="liThree">
                        <div class="circle"></div>
                        <span class="lis">参与者须支付对应船只的保证金，21 天航海周期内完成不少于 12 次航海日志记录后，保证金将全额退还；如未完成，保证金将不退还</span>
                      </div>
                    </div>
                    <div class="ulThree">
                      <div class="liThree">
                        <div class="circle"></div>
                        <span
                          class="lis">航海开船后的前三天，可申请下船并退还保证金；从第四天起，除不可抗力以外，中途退出航海不退还保证金</span>
                      </div>
                    </div>
                    <div class="ulThree">
                      <div class="liThree">
                        <div class="circle"></div>
                        <span
                          class="lis">每日仅限打卡当日的航海日志，若错过当日机会，则不支持补打卡，请合理安排打卡时间</span>
                      </div>
                    </div>
                    <img src="/assets/img_bg_ship-60f807ec.png" class="ship"></div>
                </div>
              </div>
              <div class="img"><img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAALQAAAAuCAMAAAC27sMlAAAAP1BMVEUAAAAAZ1gAZ1gAZlkAZVgAZ1gAZlgAZFgAZVkAZVoAZVkAZlkAZVkAYFAAZVsAZFcAZVYAZlkAZlkAZlkAZlkzFRLGAAAAFHRSTlMAIL/fYJ+AQL/vcK+QEBBQMM+PXwK+N2QAAAQ4SURBVGje1ZnZgqMgEEUlgMgmmvH/v3WkxCoW6TjpF+c+dOKCdSguBaaHWl68LdfbptfpHfzwH8ibaStkxfBw+VFvjfizsQMHSi1NWBhTYbT6wGbDU+XfQOxUflJJwB6HZ4pxoJub84A9PXJGAvO6XF96qEUAzHUuzjJ26HG59vxn447RIVUTxs7k799880SWhBd7N+MdXVEsn9/7JuY+tSnOrDg0attVGUtsqNJabD+WQyaz4Zmu4kiL2BiqmcIQFfMfKQN+RNkajGIpfBLpteUSfWhGvfoMrfJSxvd2TVCLH1EzrwwCzb+HDtMhDdBTKdGBXrYog2PPmqATfiBa+BdoLkH2Chpi9jV2oAcjpXTzmWg5XEIHKVV+qhpW04emyBwCGnnI/gqaJKpEt4TIpjLonhaEVvtI+wTNNxRAC74r7RIamRggk40JYpkggCSYQ/u5tSxX1X1LF1ohNGSjAw2STVo/JwUElxXVvlrWZyOiKe+/g6a6uA7fQAcsHRCskcGFMe8d0x/sUUEHccgQNOPQg7GSuAPtoKwlmZ9mxQt7gOpPRILu1WnZITJHj0Dx+PC+hu+ag65RYlX2I9xGc9RBvK+hpZQigzY3asccj1mqHi52gPjUFfTRzYkVK+70NTTc5gia6RvQIhKkklfE2a6hQTofA3D/19CQWcw0zUypcukSGlYmrNNwEaFZB9qyku5X0A7ualbEsVn+x8odCA3+6EJ7CxMAQX4PnXg8Qsso3YEu3YHQWSDd7CsN2G2da7qQV9U3R2XTmq9daA2tEzQh8gklEDp3B0GDP9zphFCU9SlC0JyrJiIFvFYPeoZH1tCFRoTO3UHQ4Cl9dsc0adYtNHjKfQ0NxeM+NLmDoMkfVH0pzdNSb5jaxcVeM+sONBSPcGWPFwrtUbqDoKE+uGZL4SCwlBz+gli5jOOROmVgGUsHcxovzgVwLoPkXKUnLzV0dyKSOwga/UEs3aVVZqPFh3vVg/YWU44z3IYmdxTQS74jcwTQyJVb0/vQEjd9VDxaaCtQDKHJHQSNLVwqCtpjekYQ30OMh1i21VF3oWnHzT22ti10IUnQ5A6CLv2xJ+GNsbpZleiOz9A4aYoXyfE2NLmjgmYYK+Ivn6ADNERQQRrBRHgYGG7iHECw8zDchiZ3EDQ1gfZzNMMHaM+LRG8/SJ6zkDNI8HQiLB88vSA0uqOBNttpZrXVBrE0H6jf7DY0xXGQYSwe96oHuaOBnsmLrmnNWHE4wg33oRcImSYM99BgvQtN7mihyR/DvH7+AdIWiX/1FWIW9s/k7f2bGub9r4FIsPIluXQ3acQzr/QEesYhsX+nl8w+tY895fPwOP3wy7l66o/q+ELPOv++eGCeo2aX3tmKLNu0lD9WggMhtyYoxpZgzn/JqeHJMnxrpM3wdIlqaZ3C8D+IBffi8KpqXXjo/PsLvOqnLUJfzOIAAAAASUVORK5CYII="
                class="sideName"></div>
            </div>
          </div>
        </div>

      </div>
      <div class="right">
        <div class="link">
          <div class="pay-box">
            <div class="price">
              <span>保证金</span>
              <div class="v-spacer"></div>
              <div class="price-num"><span>¥</span>
                <span class="large">{{price}}</span>
              </div>
            </div>
            <div class="hint">
              <div><span>未参加本次活动</span></div>
            </div>
            <div>
              <el-button style="width: 270px" @click="pay"> 报名</el-button>
<!--              <el-button style="width: 270px"> 已报名</el-button>-->
<!--              <el-button style="width: 270px"> 已停止报名</el-button>-->
            </div>

          </div>
        </div>
      </div>
      <el-dialog
        title="支付保证金"
        :visible.sync="payShow"
        :modal="false"
        width="30%"
        center>
        <span>支付金额：{{savedData.price }}</span>
        <img :src="QRImgUrl" />
      </el-dialog>
    </div>
  </div>
</template>

<script>
import templetTitle from '/components/templetTitle'
import templetChildrenUl from '/components/templetChildrenUl'
import templetContentUl from '/components/templetContentUl'
import templetContentStage from '/components/templetContentStage'
import templetContentWeek from '/components/templetContentWeek'
import QRCode from 'qrcode'
import {getQrCode} from '/api/pay'
import { getTemplateListByInfoId, getInfo } from '/api/hanghaimast'

export default {
  components: {
    templetTitle, templetChildrenUl, templetContentUl, templetContentStage, templetContentWeek
  },
  props: [ 'articleTitleName', 'articleDescribe', 'price', 'articleType', 'id' ],

  data () {
    return {
      text: '传统的商业模式是通过商业资产的成功，来成就个人的成功。但是，这样的模式投入成本大，门槛高，成功率也较低。!',
      week: '1. 每天至少刷小红书 1 小时，点赞、收藏感兴趣的、和自己同赛道的笔记，熟悉平台的同时提升账号权重\n2. 借助【实战工具包】的后羿采集器，根据航海手册【章节 2.3】搜集更多对标账号，重点拆解其变现路径，并模仿规划自身账号的变现方式\n3. 根据航海手册【章节 3.3】，每天收集 5 个爆款选题，并建立爆款选题库\n4. 每天至少收集 10 篇点赞或收藏数 \u003e100 的笔记，至少拆解 2 篇收集到的笔记\n5. 结合航海手册【章节 3.4-3.6】，每天至少像素级模仿 1 篇对标笔记并发布\n6. 间隔 1～2 小时记录一次笔记的浏览数、点赞数、收藏数、涨粉数，结合手册【章节四】观察两周内记录的笔记数据，至少完成 1 次复盘总结\n7. 尝试使用【实战工具包】中的更多工具，多角度提升小红书运营效率与笔记产出效率',
      weekList: [],
      template: [
        {
          title: '项目简介',
          content: [
            {
              text: '在 2023 微信公开课 PRO 上，视频号官方宣布，过去一年，视频号内容生态日渐繁荣，消费时长正逐渐接近朋友圈。同时，2022 年日活跃创作者数量和日均上传视频数量均同比增长 100% 以上。'
            },
            {
              text: '大量的案例和官方数据证明，视频号用户群体不低端，平均客单价高达 200 元以上，复购率平均达 60%，前景广阔。'
            },
            {
              text: '经过去年的发展，视频号生态已经逐渐规范，如果想要在视频号短视频带货领域有所成绩，需要投入更多的精力和努力。'
            },
            {
              text: '下场难度变大，也就意味着坚持这个赛道的人会变少。目前来看，视频号的实际运营效果比以前更好，同时官方也已经清理了大部分劣质内容，对视频号的整体环境也有所改善。'
            },
            {
              text: '尽管门槛有所提高，只要持续行动，仍然可以取得更好的成绩。'
            }
          ]
        },
        {
          title: '适合人群',
          content: [
            {
              text: '爱刷短视频，能及时抓住热点并找到热门商品的人',
              order: true
            },
            {
              text: '喜欢或者擅长给好友种草、热爱分享自己的生活和好物的人',
              order: true
            },
            {
              text: '没有时间、精力做直播带货，但愿意用视频带货方式变现的人',
              order: true
            }
          ]
        },
        {
          title: '上船建议',
          content: [
            {
              text: '在航海里，每一个人都会拥有自己的节奏，而在登船之前，需要大家清楚的知道，该项目必备要求是什么，加分项是什么'
            },
            {
              type: 'head',
              text: '下场建议：'
            },
            {
              text: '拥有多个实名认证的视频号账号',
              order: true
            },
            {
              text: '缴纳 100 元橱窗保证金',
              order: true
            },
            {
              type: 'head',
              text: '加分项'
            },
            {
              text: '有一定的拍摄、剪辑经验',
              order: true
            },
            {
              text: '自己有商品供应链，或者可以对接到价格优势的商品品供应链',
              order: true
            },
            {
              text: '有过线上卖货的经验，并可以根据即将到来的节日预判爆品',
              order: true
            },
            {
              text: '有较强的推销能力，擅长给好友种草，并且有一定的成交数据反馈',
              order: true
            },
            {
              type: 'delimiter',
              text: '* 「下场建议」的条件，可以在上船前不具备，但建议在开船后的一周内解决这些问题，不然很容易跟不上航海进度'
            }
          ]
        }
      ],
      payShow: false,
      QRImgUrl: '',
      QRlink: '',
      savedData: {}
    }
  },
  created () {
    const savedData = localStorage.getItem('sailingInfoData')
    this.savedData = savedData ? JSON.parse(savedData) : null
    this.selectActiveInfo()
    this.getTemplateListByInfoId()
  },
  mounted () {
    this.weekList = this.week.split('\n')
  },
  methods: {
    getTemplateListByInfoId () {
      getTemplateListByInfoId({'id': this.savedData.id}).then(res => {
        this.template = res.data
      })
    },
    selectActiveInfo () {
      let query = {
        'id': this.savedData.id
      }
      getInfo(query).then(res => {
        // eslint-disable-next-line standard/object-curly-even-spacing
        this.articleTitleName = res.data.articleTitleName
      })
    },
    async getQRcode () {
      let param = {
        device_info: 'web',
        body: '航海报名',
        detail: '[{ "goods_detail":[ { "goods_id":"iphone6s_16G", "wxpay_goods_id":"1001", "goods_name":"iPhone6s 16G", "quantity":1, "price":' + this.price * 100 + ', "goods_category":"123456", "body":"苹果手机" }]',
        attach: '沈阳分店',
        outTradeNo: '21010619930420091115',
        totalFee: this.price * 100,
        spbillCreateIp: '192.168.108.1',
        notifyUrl: 'https://chat.wenwen-ai.com/api/pay/notify',
        productId: '122354132140703564580585',
        tradeType: 'NATIVE'
      }
      await getQrCode(param).then(res => {
        this.QRlink = res.codeUrl
      })
      let opts = {
        errorCorrectionLevel: 'H', // 容错级别
        type: 'image/png', // 生成的二维码类型
        quality: 1, // 二维码质量
        margin: 3, // 二维码留白边距
        width: 240, // 宽
        height: 240, // 高
        text: this.QRlink, // 二维码内容
        color: {
          dark: '#000', // 前景色
          light: '#FFF'// 背景色
        }
      }
      // this.QRlink 生成的二维码地址url
      QRCode.toDataURL(this.QRlink, opts, (err, url) => {
        if (err) throw err
        // 将生成的二维码路径复制给data的QRImgUrl
        this.QRImgUrl = url
      })
    },
    pay () {
      this.payShow = true
      this.getQRcode()
      this.timer = setInterval(() => {
        this.queryOrderStatus()
      }, 1000)
    },
    queryOrderStatus () {

    },
    handleDecode (decodedText) {
      this.decodedText = decodedText
    }
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
@import "../../assets/style/mixin";
@import "../../assets/style/theme";

.sailing {
  padding-top: 48px;
}

.container {
  position: relative;
  display: flex;
  margin-top: 12px;
  justify-content: center;
  max-width: 1050px;
  margin: 0 auto;
}

.container .left {
  display: flex;
  flex-direction: column;
  position: relative;
  width: 720px;
}

.container .left .header {
  display: flex;
  position: relative;
  margin-bottom: 10px;
  padding: 16px 20px;
  width: 100%;
  height: 140px;
  background: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
}

.container .left .header .head-top {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.container .left .header .head-top .headImg {
  width: 100px;
  height: 100px;
  margin-right: 14px;
  border-radius: 4px;
}

.container .left .header .info {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.container .left .header .info .title {
  display: flex;
  align-items: center;
  margin-bottom: 4px;
}

.container .left .header .info .title span {
  font-weight: 600;
  font-size: 24px;
  line-height: 32px;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.container .left .header .info .hint {
  flex: 1 1 0%;
  font-weight: 400;
  font-size: 14px;
  line-height: 22px;
  text-align: left;
}

.container .left .header .info .expire {
  font-weight: 400;
  font-size: 14px;
  line-height: 22px;
  color: rgba(0, 0, 0, 0.45);
}

.container .left .content {
  display: flex;
  flex-direction: column;
  position: relative;
  background: white;
  border-radius: 4px;
}

.container .left .content .top {
  flex: 1;
  position: relative;
  padding: 16px 20px;
  font-weight: 500;
  font-size: 20px;
  line-height: 28px;
  text-align: left;
}

.container .left .content .img-box {
  display: flex;
  flex-direction: column;
  position: relative;
  padding: 20px;
  width: 100%;
  box-sizing: border-box;
}

.activeIntroduce.vc-pc {
  margin-bottom: 20px;
}

.activeIntroduce.vc-pc .cardActiveList {
  display: flex;
  flex-direction: column;
}

.activeIntroduce.vc-pc .cardActiveList .cardHeadImg {
  display: flex;
  flex-direction: column;
  position: relative;
}

.activeIntroduce.vc-pc .cardActiveList .cardHeadImg .starBg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 40px;
  z-index: 10;
}

.cardHeadImg .titleMains {
  position: absolute;
  top: 11px;
  left: 50px;
  z-index: 10;
  display: block;
}

.activeIntroduce.vc-pc .cardActiveList .cardHeadImg .titleMains .subject {
  display: flex;
}

.activeIntroduce.vc-pc .cardActiveList .cardHeadImg .titleMains .subject span {
  font-size: 18px;
  font-weight: 600;
  line-height: 18px;
  background: linear-gradient(180deg, #fff7ec 0%, #ffe0b4 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
.activeIntroduce.vc-pc .cardActiveList .cardContent {
  margin-top: 40px;
  background-color: #f7fafc;
  padding: 16px 16px 0;
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  display: flex;
  flex-direction: column;
}
.activeIntroduce.vc-pc .cardActiveList .cardContent .contentWord {
  text-align: justify;
}
.activeIntroduce.vc-pc .cardActiveList .cardContent .contentWord .div {
  margin-bottom: 16px;
}
.activeIntroduce.vc-pc .cardActiveList .cardContent .contentWord .div span {
  font-size: 14px;
  line-height: 20px;
  color: #333;
}
.right {
  position: sticky;
  //position: fixed;
  top: 0px;
  right: 0px;
  width: 320px;
  height: 100%;
  margin-left: 10px;
}

.right .link {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  border-radius: 4px;
}

.right .link .pay-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 20px;
  width: 100%;
  background: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  position: relative;
}

.right .link .pay-box .price {
  display: flex;
  position: relative;
  width: 100%;
  margin-bottom: 14px;
  font-weight: 500;
  font-size: 16px;
  line-height: 24px;
  white-space: pre-wrap;
}

.v-spacer {
  flex: 1;
}

.right .link .pay-box .price .price-num {
  font-weight: 700;
  font-size: 16px;
  color: #36a590;
}

.right .link .pay-box .price .price-num .large {
  font-size: 28px;
  font-family: DIN;
}

.right .link .pay-box .hint {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  font-weight: 400;
  font-size: 14px;
  line-height: 14px;
  color: rgb(0, 0, 0);
}

.right .link .pay-box .hint div:first-child {
  margin-bottom: 26px;
}

.right .link .pay-box .vc-btn {
  width: 280px;
  height: 44px;
}

</style>
