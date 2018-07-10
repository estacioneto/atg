const http = require('http');
const { recommend } = require('./graph_manager/recomenda');
const tryOpen = path => require('fs').readFileSync(`.${path}`);

http.createServer((req, res) => {
    const url = req.url;
    console.log(url);
    const id = url.split('=')[1];
    if (url === '/') {
        res.writeHead(200, { 'content-type': 'text/html' });
        res.write(tryOpen('/index.html'));
    } else if (url.includes('.js')) {
        res.writeHead(200, { 'content-type': 'text/javascript' });
        res.write(tryOpen(url));
    } else if (url.includes('.css')) {
        res.writeHead(200, { 'content-type': 'text/css' });
        res.write(tryOpen(url));
    } else {
        res.writeHead(200, { 'content-type': 'application/json' });
        if (url.includes('='))
            res.write(JSON.stringify(recommend(id)));
        else
            res.write(JSON.stringify({ error: 'u w0t m8' }));
    }
    res.end();
}).listen(8080);