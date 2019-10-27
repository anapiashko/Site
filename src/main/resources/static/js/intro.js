var commentApi = Vue.resource('/comment');

Vue.component('input-form', {
    props:['comments'],
    data:function(){
        return{
            text: '',
            id: ''
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="type comment" v-model="text"/>' +
        '<input type="submit" value="Send" v-on:click="save">' +
        '</div>',
    methods: {
        save: function () {
            var comment = {
                text: this.text
            };
            commentApi.save({}, comment).then(result =>
                result.json().then(data => {
                    this.comments.push(data);
                })
            )
        }

    }
});
Vue.component('full-comment', {
    props: ['comment',  'comments'],
    data: function () {
        return {
            text: ''
        }
    },
    template:
        '<div>' +
        '<small><p  style="color:#7F8C8D"> {{comment.time}}</p></small>'+
        '<b>{{comment.id}}</b><span> {{comment.text}}</span>' +
        '<span style="position: absolute; right: 0">' +
        '</span>' +
        '</div>'
});
Vue.component('comment-list', {
    props: ['comments'],
    data: function () {
        return {
            comment: null
        }
    },
    template:
        '<div>' +
        '<input-form :comments="comments" />' +
        '<full-comment v-for="comment in comments" :key="comment.id" :comment="comment" :comments="comments"  style="position: relative; width: 300px;"/>' +
        '</div>',
    created: function () {
        commentApi.get().then(result =>
            result.json().then(data =>
                    data.forEach(comment => this.comments.push(comment))
                //     console.log(data)
            )
        )
    },
});

var app = new Vue({
    el: '#app',
    template:
    '<div>'+
        '<div>In Spring Framework annotation-based configuration instead of using XML for describing the bean wiring, you have the choice to move the bean configuration into component class. It is done by using annotations on the relevant class, method or the field declaration. Before performing XML, injection annotation injection is performed. Therefore, the latter one will override the former configuration for the properties.\n' +
        'Read More about Spring Beans Autowiring Eclipse IDE Example\n' +
        'By default, Spring annotation wiring is not turned on in Spring Framework. Therefore, you need to enable it before you can use the Spring annotation-based wiring in the Spring Configuration file. Below is the configuration file in case you want to use annotation in your application:'+  '</div>'+
        '<comment-list :comments = "comments"/>'+
    '</div>',
    data:{
        comments: []
    }
});
