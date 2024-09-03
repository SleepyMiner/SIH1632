import {
  Bar,
  Button,
  Card,
  CardHeader,
  Dialog,
  List,
  ListItemStandard,
  Title,
} from "@ui5/webcomponents-react";
import Filters from "../components/Filters";
import { BASE_URL } from "../utils/config";
import axios from "axios";
import { useEffect, useState } from "react";
import {
  useWorkModeState,
  useCompanySearchState,
  useJobSearchState,
  useSectorState,
  useAppliedJobState,
} from "../store/Store";
import { PiSuitcaseDuotone, PiBuildingOffice } from "react-icons/pi";
import { FaExternalLinkAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

export default function JobPage() {
  const [jobs, setJobs] = useState([]);
  const [dialogOpen, setDialogOpen] = useState<boolean>(false);
  const [selectedJobData, setSelectedJobData] = useState<any>({});
  const { selectedWorkMode } = useWorkModeState();
  const { selectedCompany } = useCompanySearchState();
  const { searchedJob } = useJobSearchState();
  const { selectedSector } = useSectorState();
  const { setAppliedJob } = useAppliedJobState();
  const navigate = useNavigate();

  const fetchJobs = () => {
    const params: any = {};

    if (selectedWorkMode !== "All") {
      params.workMode = selectedWorkMode;
    }
    if (selectedCompany) {
      params.organizationName = selectedCompany;
    }
    if (searchedJob) {
      params.title = searchedJob;
    }
    if (selectedSector !== "All") {
      params.sector = selectedSector;
    }

    if (Object.keys(params).length === 0) {
      axios.get(`${BASE_URL}/jobs`).then((response) => {
        setJobs(response.data._embedded.jobs);
      });
    } else {
      axios
        .get(
          `${BASE_URL}/jobs/search/findAllByTitleOrOrganizationNameOrWorkModeOrSector`,
          { params }
        )
        .then((response) => {
          setJobs(response.data._embedded.jobs);
        });
    }
  };

  const calculateTime = (t: any) => {
    const now = new Date();
    const pastDate = new Date(t);

    const diffInMs = now.getTime() - pastDate.getTime();

    const diffInSeconds = Math.floor(diffInMs / 1000);
    const diffInMinutes = Math.floor(diffInSeconds / 60);
    const diffInHours = Math.floor(diffInMinutes / 60);
    const diffInDays = Math.floor(diffInHours / 24);

    if (diffInDays > 0) {
      if (diffInDays > 1) {
        return `${diffInDays} days ago`;
      } else {
        return `${diffInDays} day ago`;
      }
    } else if (diffInHours > 0) {
      if (diffInHours > 1) {
        return `${diffInHours % 24} hours ago`;
      } else {
        return `${diffInHours % 24} hour ago`;
      }
    } else if (diffInMinutes > 0) {
      if (diffInMinutes > 1) {
        return `${diffInMinutes % 60} minutes ago`;
      } else {
        return `${diffInMinutes % 60} minute ago`;
      }
    }
  };

  const handleJobClick = (e: any) => {
    axios
      .get(
        `${BASE_URL}/jobs/${e.target.attributes["accessible-name"].textContent}`
      )
      .then((response) => {
        setSelectedJobData(response.data);
        setDialogOpen(true);
      });
  };

  const handleJobApply = (id: string) => {
    setAppliedJob(id);
    console.log(id);
    navigate("/apply");
  };

  const handleJobClose = () => {
    setDialogOpen(false);
    setSelectedJobData([]);
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  useEffect(() => {
    fetchJobs();
  }, [selectedWorkMode, selectedCompany, searchedJob, selectedSector]);

  return (
    <>
      <div className="p-2">
        <Filters />
      </div>
      <div>
        {jobs.map((job) => (
          <Card
            header={
              <CardHeader
                subtitleText={job["organizationName"]}
                titleText={job["title"]}
                onClick={handleJobClick}
                additionalText={`Posted: ${calculateTime(job["postedOn"])}`}
              />
            }
            style={{
              width: "400px",
            }}
            className="p-4"
            key={job["id"]}
          >
            <List>
              <ListItemStandard description={job["description"]}>
                Job Description
              </ListItemStandard>
              <ListItemStandard description={job["sector"]}>
                Sector
              </ListItemStandard>
              <ListItemStandard description={job["workMode"]}>
                Work Mode
              </ListItemStandard>
              <ListItemStandard description={job["workHours"]}>
                Work Hours
              </ListItemStandard>
              <ListItemStandard className="pb-1">
                <Button
                  onClick={handleJobClick}
                  design="Attention"
                  accessibleName={job["id"]}
                  className="item-center flex"
                >
                  Details
                </Button>
              </ListItemStandard>
            </List>
          </Card>
        ))}
      </div>
      {dialogOpen ? (
        <Dialog
          open={dialogOpen}
          header={<Bar startContent={<Title level="H3">Details</Title>} />}
          footer={
            <Bar
              design="Footer"
              endContent={<Button onClick={handleJobClose}>Close</Button>}
            />
          }
          className="w-96"
        >
          <div className="flex flex-col">
            <div className="flex items-center gap-2 text-xl">
              <PiBuildingOffice />
              <p>{selectedJobData["organizationName"]}</p>
            </div>
            <p className="text-2xl font-semibold">{selectedJobData["title"]}</p>
            <p className="text-sm text-gray-500 mx-0.5 pt-0.5">
              Posted: {calculateTime(selectedJobData["postedOn"])}
            </p>
            <div className="flex gap-2 items-center">
              <PiSuitcaseDuotone size={20} />
              <p>
                {" - "}
                {selectedJobData["type"][0].toUpperCase() +
                  selectedJobData["type"].slice(1)}
              </p>
              <p>
                {" - "}
                {selectedJobData["workMode"]} - {selectedJobData["sector"]}
              </p>
            </div>
            <Button
              className="flex w-32 mt-4 rounded-xl"
              design="Emphasized"
              onClick={() => handleJobApply(selectedJobData["id"])}
            >
              <div className="flex gap-4">
                <p>Apply</p> <FaExternalLinkAlt size={15} />
              </div>
            </Button>

            <div className="mt-6">
              <p className="font-semibold text-xl">About the Job:</p>
              <div className="flex gap-1">
                <p className="font-semibold">Job Title: </p>
                <p>{selectedJobData["title"]}</p>
              </div>
              <div className="flex gap-1">
                <p className="font-semibold">Company: </p>
                <p>{selectedJobData["organizationName"]}</p>
              </div>
              <div className="flex gap-1">
                <p className="font-semibold">Work Hours: </p>
                <p>{selectedJobData["workHours"]}</p>
              </div>
              <div className="flex gap-1">
                <p className="font-semibold">Sector: </p>
                <p>{selectedJobData["sector"]}</p>
              </div>
              <div className="flex gap-1">
                <p className="font-semibold">Description: </p>
                <p>{selectedJobData["description"]}</p>
              </div>
            </div>
          </div>
        </Dialog>
      ) : (
        <></>
      )}
    </>
  );
}
