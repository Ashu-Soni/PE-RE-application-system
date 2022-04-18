import { Col, Row, Table, Button, Input, Modal} from 'antd';
import Column from 'antd/lib/table/Column';
import Layout, { Content, Header } from 'antd/lib/layout/layout';
import React, { Component, useState } from 'react';
import ApplyForm from './ApplyForm';

const { Search } = Input;

let projs = [
  {
    key: '1',
    name: 'PE/RE Application System',
    professor: "ABC",
    description: "Hello world",
    slots: 10,
  },
  {
      key: '2',
      name: 'Health care management',
      professor: "sdfsdf",
      description: "Hello world",
      slots: 4,
  },
];

export default class RE extends Component {
  editFormRef = React.createRef();
  constructor(props){
    super(props);
    this.state = {
      columns: [],
      projects: [],
      filtered: [],
      applyProject: {},
      applyModal: false,
    }
    this.editModalForm = this.editModalForm.bind(this)
    this.onCancelApply = this.onCancelApply.bind(this)
  }

  componentDidMount = () => {
    // let url = `http://localhost:9090/re`;
    // fetch(url, {
    //   method: "GET",
    //   headers: {
    //     "Content-Type": "application/json",
    //     "Access-Control-Allow-Origin": "*",
    //   },
    // })
    //   .then((res) => res.json())
    //   .then((response) => {
    //     if (response.status) {
    //       this.setState({projects: response.projs, filtered: response.projs})
    //     } else {
    //       message.error(response.message, 5);
    //     }
    //   })
    //   .catch((err) => console.log(err));
    this.setState({projects: projs, filtered: projs})
  }

  onSearch = (value) => {
    let data = this.state.projects;
    
    if (value.length === 0) {
      this.setState({filtered: data})
      return;
    }
    let searchLower = value.toLowerCase();
    let filtered_projects = data.filter((item) => {
      console.log(item.professor)
      if (item.professor.toLowerCase().includes(searchLower)) {
        return true;
      }
      return false;
    });
    this.setState({filtered: filtered_projects})
  };

  onSearchChange = (e) => {
    if (e.target.value.length === 0) {
      this.onSearch("");
    }
  };

  editModalForm = () => {
    let record = this.state.applyProject;
    console.log(record)
    this.editFormRef.current.setFieldsValue({
      name: record.name,
      professor: record.professor,
      description: record.description,
    });
  }

  onApply = (record) => {
    console.log(record);
    this.setState({applyProject: record, applyModal: true})
  }

  onCancelApply = () => {
    this.setState({applyModal: false})
  }


  render(){   
    const {filtered } = this.state;

    return(
      <Layout>
        <Header style={{backgroundColor: 'whitesmoke'}}>
          <Search
            placeholder="Search by Professor Name"
            allowClear
            style={{ width: "80%", marginTop: "15px", marginLeft: "8%" }}
            onSearch={this.onSearch}
            name="professorSearch"
            onChange={this.onSearchChange}
          />
        </Header>
        <Content>
          <Table dataSource={filtered}>
            <Column key="name" dataIndex={"name"} title="Name"/>
            <Column key ="description" dataIndex={"description"} title="Description"/>
            <Column dataIndex="slots" title="Slot"/>
            <Column dataIndex="professor" title="Professor"/>
            <Column key="action" render={(r) => {
              return(
                <Button type='primary' onClick={() => this.onApply(r)}>Apply</Button>
              )
            }} />
          </Table>
          <Modal
            visible={this.state.applyModal}
            title="Application Form"
            footer={null}
          >
            <ApplyForm {...this}{...this.state}></ApplyForm>
          </Modal>
        </Content>
      </Layout>
    )
  }
}