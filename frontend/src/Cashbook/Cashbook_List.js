import React, { Component } from 'react';
import Cashbook_Info from "./Cashbook_Info";

export default class Cashbook_List extends Component {


    render() {
        const {data} = this.props;

        const list = data.map(
            info =>(
                <Cashbook_Info key={info.cash_seq} info={info} />
            )
        );

        return (


            <div className="content-box" style={{overflow:"auto"}}>

                <table className={"table"}>
                    <thead>
                        <tr>
                            <th style={{width:"200px"}}>날짜</th>
                            <th className={"text-right"}>입출금액</th>
                            <th className={"text-right"}>현금 총잔액</th>
                        </tr>
                    </thead>
                    <tbody>
                        {list}
                    </tbody>
                </table>

            </div>

        );
    }
}

