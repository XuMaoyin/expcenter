module.exports = {
    /*跨域配置第二步，配置公共头部对应的内容，并允许跨域*/
    devServer: {
        proxy: {
            '/api': {
                // 此处的写法，目的是为了 将 /api 替换成 https://www.baidu.com/
                target: 'http://localhost:8002/',
                // 允许跨域
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        },
        disableHostCheck:true,
        open:false,
        host:'localhost',
        port:8088
    }
}
