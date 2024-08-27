import {
  FilterBar,
  FilterGroupItem,
  Input,
  Select,
  Option,
} from "@ui5/webcomponents-react";
import {
  useWorkModeState,
  useJobSearchState,
  useCompanySearchState,
  useSectorState,
} from "../store/Store";

export default function Filters() {
  const { setSelectedWorkMode } = useWorkModeState();
  const { setsearchedJob } = useJobSearchState();
  const { setSelectedCompany } = useCompanySearchState();
  const { setSelectedSector } = useSectorState();

  const handleWorkModeChange = (e: { detail: any }) => {
    setSelectedWorkMode(e.detail.selectedOption.innerText);
  };

  const handleJobSearch = (e: { target: any }) => {
    setsearchedJob(e.target.typedInValue);
  };

  const handleCompanySearch = (e: { target: any }) => {
    setSelectedCompany(e.target.typedInValue);
    console.log(e.target.typedInValue);
  };

  const handleSectorChange = (e: { detail: any }) => {
    setSelectedSector(e.detail.selectedOption.innerText);
  };

  return (
    <div>
      <FilterBar filterContainerWidth="13.125rem">
        <FilterGroupItem
          label="Company Search"
          filterKey="companySearch"
          groupName="Group 1"
        >
          <Input onChange={handleCompanySearch} placeholder="Company Name" />
        </FilterGroupItem>
        <FilterGroupItem
          label="Job Search"
          filterKey="jobSearch"
          groupName="Group 2"
        >
          <Input onChange={handleJobSearch} placeholder="Job Title" />
        </FilterGroupItem>
        <FilterGroupItem
          label="Work Mode"
          filterKey="workMode"
          groupName="Group 3"
        >
          <Select onChange={handleWorkModeChange}>
            <Option selected>All</Option>
            <Option>On-Site</Option>
            <Option>Remote</Option>
            <Option>Hybrid</Option>
          </Select>
        </FilterGroupItem>
        <FilterGroupItem label="Sector" filterKey="sector" groupName="Group 4">
          <Select onChange={handleSectorChange}>
            <Option selected>All</Option>
            <Option>Private</Option>
            <Option>Government</Option>
            <Option>Foreign</Option>
          </Select>
        </FilterGroupItem>
      </FilterBar>
    </div>
  );
}
