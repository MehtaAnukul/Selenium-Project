package datafactory.event;

import dataobjects.roamy.admin.event.list.Filter;
import dataobjects.roamy.admin.event.list.Filter__1;
import dataobjects.roamy.admin.event.list.Sort;

import java.util.ArrayList;
import java.util.List;

public class EventList
{
    public List<Sort> getEventListCreatedAtAsc()
    {
        Sort sortData =new Sort();
        sortData.setField("created_at");
        sortData.setDir("Desc");

        List<Sort> listSort = new ArrayList<>();
        listSort.add(sortData);
        return listSort;
    }

    public Filter getEventList()
    {
        Filter__1 filterDetailData = new Filter__1();
        filterDetailData.setField("status_name");
        filterDetailData.setOperator("eq");
        filterDetailData.setValue("Draft");

        List<Filter__1> filterData = new ArrayList<>();
        filterData.add(filterDetailData);

        Filter filterList = new Filter();
        filterList.setLogic("And");
        filterList.setFilters(filterData);

        return filterList;
    }
}
