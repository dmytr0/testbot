requirejs.config({
    baseUrl: "js"
});


function buildRoute(view) {
    return function () {
        webix.ui({
            id: 'content',
            rows: [
                view
            ]
        }, $$('content'))
    }
}

function buildButton(label, route) {
    return {
        view: 'button',
        value: label,
        width: 100,
        align: 'center',
        click: function () {
            routie(route)
        }
    }
}


require(['views/main', "views/list"], function (main, list) {
    webix.ready(function () {
        webix.ui({
            container: "app",
            rows: [
                {
                    view: "toolbar",
                    cols: [
                        buildButton("Home", ""),
                        buildButton("List", "list"),
                        {gravity: 3}
                    ]
                },
                {
                    id: "content",
                    row: []
                }
            ]
        });


        routie({
            '': buildRoute(main),
            'list': buildRoute(list)
        })
    });

});


