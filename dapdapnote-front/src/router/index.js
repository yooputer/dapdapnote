import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue';
import MyNote from '@/views/MyNote.vue';
import GroupNote from '@/views/GroupNote.vue';

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
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;