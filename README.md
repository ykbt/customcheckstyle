# customcheckstyle
original checkstyle checkers

# usage

## gradle options

```groovy
repositories {
  maven { url 'https://ykbt.github.io/customcheckstyle/' }
}

dependencies {
  implementation 'com.ykbt:customcheckstyle:0.0.2'
}
```

## checkstyle.xml

### NotThrownExceptionCheck

```xml
<module name="Checker">
  <module name="TreeWalker">
    <module name="com.ykbt.customcheckstyle.checks.coding.NotThrownExceptionCheck">
      <property name="format" value=".*"/>
      <property name="severity" value="warning"/>
    </module>
  </module>
</module>
```
