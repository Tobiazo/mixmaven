module mixmaven.core {
    requires com.google.gson;
    //TODO: look into encapsulation
    opens core to com.google.gson;
    exports core;
}
