update orders set
    state='CLOSED',
    closing_price=(select current_rate from rates where pair=orders.pair)
where id in (
    select ot.order_id from
        order_triggers as ot join
        orders as o on ot.order_id = o.id join
        rates as r on r.pair=o.pair where
            o.state='OPENED' and
            ot.type in ('MAKEPROFIT','STOPLOSS') and
            is_level_crossed(ot.trigger,r.current_rate,r.previous_rate)
    );

update orders set
    state='OPENED',
    opening_price=(select current_rate from rates where pair=orders.pair)
where id in (
    select ot.order_id from
        order_triggers as ot join
        orders as o on ot.order_id = o.id join
        rates as r on r.pair=o.pair where
            o.state='WAIT' and
            ot.type='OPEN' and
            is_level_crossed(ot.trigger,r.current_rate,r.previous_rate)
    );

update orders set
    state='CLOSED',
    closing_price=(select current_rate from rates where pair=orders.pair)
where id in (
    select ot.order_id from
        order_triggers as ot join
        orders as o on ot.order_id = o.id join
        rates as r on r.pair=o.pair where
            o.state='OPENED' and
            (select count(*) from order_triggers as ot1 where ot1.order_id=o.id and
            ot1.type in ('MAKEPROFIT','STOPLOSS') and
            is_level_crossed(ot1.trigger,r.current_rate,r.previous_rate))=3
    );