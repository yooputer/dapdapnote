import { createRouter, createWebHistory } from 'vue-router';
import NoteList from '@/views/NoteList.vue';
import CreateNote from '@/views/EditNote.vue'
import EditNote from '@/views/EditNote.vue'

const routes = [
    {
        path: '/',
        name: 'NoteList',
        component: NoteList
    },
    {
        path: '/create',
        name: 'CreateNote',
        component: CreateNote
    },
    {
        path: '/edit/:noteSeq',
        name: 'EditNote',
        component: EditNote
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;