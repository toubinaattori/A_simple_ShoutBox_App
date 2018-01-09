module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        browserify: {
            build: {
                src: 'hello.js',
                dest: '../webapp/javascript/hello.js'
            },
            options: {
                transform: [
                    ['babelify', {"presets": ["es2015"]}],
                ],
                browserifyOptions: {debug: 'debug'}
            }
        },
        uglify: {
            robot_client: {
                files: {
                    '../webapp/javascript/hello.js': ['../webapp/javascript/hello.js']
                }
            }
        },
        watch: {
            scripts: {
              files: ['*.js'],
              tasks: ['browserify','uglify']
            },
          }
    });
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-browserify');
    grunt.registerTask('default', [
        'browserify',
        'uglify'
    ]);
};