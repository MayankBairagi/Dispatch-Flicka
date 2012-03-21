Dispatch Flika
==============

This is a sample application for [Dispatch][1], to build and run
with [simple-build-tool][2].

[1]: http://dispatch.databinder.net/

    $ sbt
    ...
    > update
    ...
    > run
    ... (authorize the app with a browser, note pin)
    > run <pin>
    > run





### Character Encodings
Add "-Dfile.encoding=UTF-8" to your sbt start script if you want non-ASCII characters to render in utf-8, which your terminal may correctly display.

[2]: http://code.google.com/p/simple-build-tool/
