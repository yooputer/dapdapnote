import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue'
import NoteList from '@/views/NoteList.vue';
import CreateNote from '@/views/EditNote.vue'
import EditNote from '@/views/EditNote.vue'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/note/list',
        name: 'NoteList',
        component: NoteList
    },
    {
        path: '/note/create',
        name: 'CreateNote',
        component: CreateNote
    },
    {
        path: '/note/edit/:noteSeq',
        name: 'EditNote',
        component: EditNote
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;