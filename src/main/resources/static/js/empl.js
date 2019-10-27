function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        } else {
            return -1;
        }
    }
}

var roleApi = Vue.resource('/roles{/id}');

Vue.component('input-form', {
    props: ['roles', 'roleAttr'],
    data: function () {
        return {
            position: '',
            id: '',
            count: ''
        }
    },
    watch: {
        roleAttr: function (newVal, oldVal) {
            this.position = newVal.position;
            this.count = newVal.count;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="edited position" v-model="position"/>' +
        '<input type="number" placeholder="amount" v-model="count"/>' +
        '<input type="submit" value="Save" v-on:click="save">' +
        '</div>',
    methods: {
        save: function () {
            var role = {
                position: this.position,
                count: this.count
            };
            if (this.id) {
                roleApi.update({id: this.id}, role).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.roles, data.id);
                        this.roles.splice(index, 1, data);
                        this.position = '';
                        this.count = '';
                        this.id = ''
                    })
                )
            } else {
                roleApi.save({}, role).then(result =>
                    result.json().then(data => {
                        this.roles.push(data);
                        this.position = '';
                        this.count = '';
                    })
                )
            }
        }
    }
});
Vue.component('full-role', {
    props: ['role', 'editRole', 'roles'],
    data: function () {
        return {
            position: '',
            count: '',
            id: ''
        }
    },
    template:
        '<div>' +
        '<b>{{role.id}}</b><span> {{role.position}}</span><i> {{role.count}}</i>' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" v-on:click="edit"/> ' +
        '<input type="button" value="X" v-on:click="del"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editRole(this.role);
        },
        del: function () {
            roleApi.remove({id: this.role.id}).then(result => {
                if (result.ok) {
                    this.roles.splice(this.roles.indexOf(this.role), 1);
                }
            })
        }
    }
});
Vue.component('roles-list', {
    props: ['roles'],
    data: function () {
        return {
            role: null
        }
    },
    template:
        '<div>' +
        '<input-form :roles="roles" :roleAttr="role"/>' +
        '<full-role v-for="role in roles" :key="role.id" :role="role" :editRole="editRole" :roles="roles"  style="position: relative; width: 300px;"/>' +
        '</div>',
    created: function () {
        roleApi.get().then(result =>
            result.json().then(data =>
                    data.forEach(role => this.roles.push(role))
                //     console.log(data)
            )
        )
    },
    methods: {
        editRole: function (role) {
            this.role = role;
        }
    }
});
var app = new Vue({
    el: '#app',
    template:
        '<roles-list :roles="roles"/>',
    data: {
        roles: []
    }
});
