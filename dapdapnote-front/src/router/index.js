import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue';
import MyNote from '@/views/MyNote.vue';
import GroupNote from '@/views/GroupNote.vue';
import EditNote from '@/views/EditNote.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/my',
        name: 'MyNote',
        component: MyNote
    },
    {
        path: '/group/:groupSeq',
        name: 'GroupNote',
        component: GroupNote
    },
    {
        path: '/edit',
        name: 'EditNote',
        component: EditNote
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;