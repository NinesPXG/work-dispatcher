package com.pxg.demo.config;

import org.apache.shardingsphere.sharding.api.sharding.ShardingAutoTableAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

/**
 * autoTables sharding sheet suffix is relative to the index of collection,start as 0->n
 * todo shardingAlgorithm
 */
public class CustomShardingAlgorithm implements StandardShardingAlgorithm<String>, ShardingAutoTableAlgorithm {


    @Override
    public int getAutoTablesAmount() {
        return 0;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        return null;
    }

    @Override
    public String getType() {
        return "CUSTOM";
    }
}
