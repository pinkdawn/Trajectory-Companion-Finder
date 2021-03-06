package tc;

import common.data.TCPolyline;
import common.data.TCRegion;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Map;

public class SubPartitionToPolylinesMapper implements
        PairFunction<Tuple2<Long, TCRegion>, String, Map<Integer, TCPolyline>>
{
    @Override
    public Tuple2<String, Map<Integer, TCPolyline>> call(Tuple2<Long, TCRegion> input) throws Exception {
        return apply(input);
    }

    public Tuple2<String, Map<Integer, TCPolyline>> apply(Tuple2<Long, TCRegion> input) {
        TCRegion region = input._2();
        long slotId = input._1();
        int regionId = region.getRegionId();
        return new Tuple2(
                String.format("%s,%s", slotId, regionId),
                region.getPolylines());
    }
}
