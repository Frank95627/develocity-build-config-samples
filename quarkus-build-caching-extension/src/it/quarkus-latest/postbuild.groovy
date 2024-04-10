File buildCacheDisabledLog = new File( basedir, 'build-cache-disabled.log' )
File buildCacheMissLog = new File( basedir, 'build-cache-miss.log' )
File buildCacheHitLog = new File( basedir, 'build-cache-hit.log' )

println(basedir)

if(buildCacheHitLog.exists()) {
    println('Verifying build cache hit...')
    assert buildCacheHitLog.text.contains( '[quarkus-build-caching-extension] Quarkus build goal marked as cacheable' )
    assert !buildCacheHitLog.text.contains( 'Quarkus augmentation completed' )
} else if(buildCacheMissLog.exists()) {
    println('Verifying build cache miss...')
    assert buildCacheMissLog.text.contains('[quarkus-build-caching-extension] Quarkus build goal marked as cacheable')
    assert buildCacheMissLog.text.contains('Quarkus augmentation completed')
} else if(buildCacheDisabledLog.exists()) {
    println('Verifying build cache disabled...')
    assert buildCacheDisabledLog.text.contains( '[quarkus-build-caching-extension] Quarkus build goal marked as not cacheable' )
    assert buildCacheDisabledLog.text.contains( 'Quarkus augmentation completed' )
} else {
    throw new IllegalStateException('Unknown context')
}

println('Verification succeeded')
