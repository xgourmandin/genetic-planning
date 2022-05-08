import { defineNuxtConfig } from 'nuxt'

// https://v3.nuxtjs.org/api/configuration/nuxt.config
export default defineNuxtConfig({

    ...process.env.NODE_ENV === 'development' && {
        proxy: {
            '/api': 'http://localhost:8080',
        }
    },

})
